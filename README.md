### 小记
最近复习了Java IO模型这块知识，也看了一点书，但是就是觉得自己如果不玩一下实战，理解永远不够深入，记忆点太浅，所以我就想着乘着现在还有时间，就去多实战实战。先做了一个基于NIO模型[简易版的多人聊天室](https://github.com/ScDadaguo/nio_chat_room)和一个简易版BIO tomcat初步了解了如何IO编程，了解了一些基础步骤。

### NIO编程实现步骤
1. 创建Selector
2. 创建ServerSocketChannel，并绑定监听端口
3. 讲channel设置为非阻塞模式
4. 将channel注册到selector上，监听连接事件
5. 循环调用selector的select方法，检查就绪情况
6. 调用selectedKey方法获取就绪channel集合
7. 判断就绪事件种类，调用业务处理方法
8. 根据业务需要决定是否再次注册监听事件，重复执行第三步的操作

附上我的笔记[笔记](http://www.dadaguo.mobi/article/71)

写完这个聊天室之后,感觉也只是一个小demo,没啥意思。想到之前[室友](https://github.com/xiantang)一直说在写一个基于NIO的服务器,我去fork了一下,看了一下感觉挺有意思的,相比之前写的BIO服务器,他换上了NIO模型,同时对代码的健壮性和基础功能进行了拓展,我决定我也试着写一下.

首先我试着去读了一下Tomcat的源码,基本了解了整个的一个套路,发现项目中基本就是利用Tomcat的特性在复写.

其实写整个项目最重要的是啥，你只要去看懂下面的那张模型图，盯着看盯着看，很重要。看个10分钟，走代码debug一圈，对着我下面写的组件间的解释，很快就能搞懂这个迷你Tonmcat的流程。

# 项目中涉及的几个重要组件.

## poller

### poller是什么

 负责处理已建立连接的socket，将channel封装后，提交至线程池（Executors）来处理。Poller线程的数目与运行时环境有关，通过计算得出，不可配置。

Poller是实现了Runnable接口的，在NioEndpoint的时候，会初始化pollers数组，同时启动pollers数组中的线程，让pollers开始工作。

每个Poller都有一个自己的Selector对象，在Poller的构造函数中，通过调用Selector.open方法生成，虽然看上去这很像是一个单例模式，但实际上没法返回的都是一个全新的对象（可能与jdk的底层实现有关，目前从Oracle提供的jdk的试验来看，两次调用返回的是不同的对象）。

------

关于nio，Channel、Selector和SelectionKey 简单理解：

- Channel必须注册到Selector上才能用于接收socket数据
- 在Selector上有数据到达的Channel可以用SelectionKey来表示

------

### 注册

1. Poller使用nio来进行socket数据的读写，一个通过Poller的register方法，注册到Poller上。
2. 对Poller的注册，先进入Poller内部维护的一个事件队列上。
3. Poller线程在执行过程中会去检查队列，将channel注册到selector上。为了保证在多线程同时访问时数据的一致性，这个队列是一个SynchronizedQueue，使用synchronized来保证对队列中数据的一致性。
4. 注册的时候，每个channel会有KeyAttachment对象，用来进行channel上的多线程并发执行时的控制。

*** ServerSocketChannel建立连接是在Acceptor上。***

------

### 队列

队列定义如下 `Queue<PollerEvent> events= new ConcurrentLinkedQueue<>();`
队列中的每个元素是PollerEvent对象，它携带完成的处理channel相关的信息。每个事件在处理过程中，会根据事件的状态，来实现Channel到Selector上的注册。队列处理完成后，每个注册到Poller上的channel就完成了到Selector上的注册。

------

### Poller数据读取

1. Poller线程的run方法的主题部分使用while(true)的无限循环来执行，当所属的Endpoint正常运行的时候，在每次执行过程中，处理其事件队列，调用selector来读取数据，然后处理读取到的数据。
2. 在run方法中，会调用 events方法来处理事件队列。
3. 调用selector.selectNow或selector.select(xxx)来获取有数据到达的channel。

------

### Poller缓存

- eventCache
  eventCache是PollerEvent事件的缓存，在Poller上注册的时候，从eventCache中取出PollerEvent对象，重置这个对象，然后再放入Poller的事件队列中。Poller在处理队列的过程中，每从队列中取出一个要处理的PollerEvent事件，处理完之后，把这个PollerEvent对象放回缓存中。   ---- 避免频繁地创建PollerEvent对象和GC回收。
- keyCache
  keyCache是对应的socket信息的缓存，在Poller上注册的时候，从keyCache中取出KeyAttachment对象，重置这个对象，作用附件用于channel到selector上的注册。在Processor处理完数据之后，将这个KeyAttachment对象放回keyCache中。  ----- 避免频繁地创建KeyAttachment对象和GC回收。

### 多线程并发控制

events队列，为ConcurrentLinkedQueue<PollerEvent>，ConcurrentLinkedQueue提供的offer、poll、size和clear方法都使用了CAS保证读写的安全。

系统中是同时有多个Poller线程在运行的，每个Polle线程有各自的events队列

## Acceptor

### 前提

nio中实现socket连接所需的基础知识:SocketChannel和ServerSocketChanne：

SocketChannel和ServerSocketChannel的概念与基础的阻塞式的java 网络编程中的socket和serversocket类似。
在后者的模型中，服务器端生成一个serversocket对象，bind绑定端口号，然后accept阻塞等待客户端连接到服务器。每当连接建立成功，则返回一个Socket对象，用来表示连接建立成功。
在java nio中。ServerSocketChannel和SocketChannel模型其实是与ServerSocket和Socket模型对应的。服务器端open打开一个ServerSocketChannel，这个操作同时会成功一个ServerSocket对象，但生成的ServerSocket对象是没有进行端口绑定的。所以在进行网络监听之前，还需要对ServerSocketChannel的ServerSocket对象进行bind绑定操作。然后ServerSocketChannel开始进行accept网络监听，请求建立成功之后返回一个SocketChannel对象。这个SocketChannel对象有一个与之对应的Socket对象。
ServerSocketChannel是线程安全的 ---- at least the Oracle documentation says so.

### Session and Cookie

Session是服务器创建，服务器保存，Cookie是服务器创建，客户端保存。第一次访问时的response会带上一个名为JSESSIONID的Cookie，每个JSESSIONID对应着一个session。以后浏览器的每次访问该网站的请求都会带上这个JSESSIONID，服务器通过这个Id来唯一标识一次会话，取出对应的session域，实现会话的维持。 

关于Session&Cookie的实现，涉及Request和ServletContext类。

- Request

```
 public HTTPSession getSession() {
        if (session != null) {
            return session;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getKey().equals("JSESSIONID")) {
                log.info("servletContext:{}",servletContext);
                HTTPSession currentSession = servletContext.getSession(cookie.getValue());
                if (currentSession != null) {
                    this.session = currentSession;
                    return session;
                }
            }
        }
        session = servletContext.createSession(requestHandler.getResponse());
        return session;
    }

```

- ServletContext

```java
    public HTTPSession getSession(String JSESSIONID) {
        return sessions.get(JSESSIONID);
    }

    public HTTPSession createSession(Response response){
        HTTPSession session = new HTTPSession(UUIDUtil.uuid());
        sessions.put(session.getId(),session);
        response.addCookie(new Cookie("JSESSIONID",session.getId()));
        return session;
    }
```



### Filter

1. 过滤器主要由Filter和Filterchain两个类组成
2. ServeltContext在初始化的时时候把配置文件中所有的filter加载出来
3. Filter的 doFilter() 就是一层一层条件是不是符合你定义的规则，符合就往下走
4. 在RequestHandler中，调用 FilterChain的doFilter() 和Filter中的doFilter()配合，一直递归执行
5. - 如果不放行，那么会在sendRedirect之后将响应数据写回客户端，结束；
   - 如果所有Filter都执行完毕，那么会调用service方法，执行servlet逻辑

*** 利用Session判断登陆状态就可以利用Filter中的一层判断 ，然后进行重定向啥的。***

### Listener

1. EventListener 核心类
2. 可以在源码里设置一些核心的监听类，业务复杂后可以继承接口，做更复杂的业务
3. 目前具有 ServletContextListener ,HttpSessionListener,ServletRequestListener,分别监听三个主要类。

```
for (HttpSessionListener listener : httpSessionListeners) {
            listener.sessionCreated(httpSessionEvent);
        }
```

```
public interface HttpSessionListener extends EventListener {
    /**
     * session创建
     * @param se
     */
    void sessionCreated(HttpSessionEvent se);

    /**
     * session销毁
     * @param se
     */
    void sessionDestroyed(HttpSessionEvent se);
    
}

```

```
public class ServletContextAndSessionListener implements ServletContextListener, HttpSessionListener {
    private AtomicInteger sessionCount = new AtomicInteger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("servlet context init...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("servlet context destroy...");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session created, count = {}", this.sessionCount.incrementAndGet());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session destroyed, count = {}", sessionCount.decrementAndGet());
    }
}
```

### 过期Socket连接清除

服务器启动后会开启一个线程，不断的遍历所有的poller，检查注册在其中的SocketChannel是否空闲超时，如果超时，从selector上反注册消除。



```
public void cleanTimeoutSockets() {
        for (Iterator<Map.Entry<SocketChannel, NioSocketWrapper>> it = sockets.entrySet().iterator(); it.hasNext(); ) {
            NioSocketWrapper wrapper = it.next().getValue();
            log.info("缓存中的socket:{}", wrapper);
            if (!wrapper.getSocketChannel().isConnected()) {
                log.info("该socket已被关闭");
                it.remove();
                continue;
            }
            if (wrapper.isWorking()) {
                log.info("该socket正在工作中，不予关闭");
                continue;
            }
            if (System.currentTimeMillis() - wrapper.getWaitBegin() > nioEndpoint.getKeepAliveTimeout()) {
                // 反注册
                log.info("{} keepAlive已过期", wrapper.getSocketChannel());
                try {
                    wrapper.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                it.remove();
            }
        }
    }
```





# 简化版Web服务器（包括HTTP服务器和Servlet容器）
## 具备的功能(均为简化版的实现)：
- HTTP Protocol
- Servlet
- ServletContext
- Request
- Response
- Dispatcher
- Static Resources & File Download
- Error Notification
- Get & Post & Put & Delete
- web.xml parse
- Forward
- Redirect
- Simple TemplateEngine
- session
- cookie
- filter
- listener 

## 使用技术
基于Java NIO、多线程、Socket网络编程、XML解析、log4j/slf4j日志
基于Spring的PathMatcher实现SpringMVC风格的路径匹配

## NIO Reactor
多个（1个或2个）Acceptor阻塞式获取socket连接，然后多个Poller（处理器个数个）非阻塞式轮询socket读事件，检测到读事件时将socket交给线程池处理业务逻辑
实现HTTP的keep-alive（复用socket连接）

![image](http://markdown-1252651195.cossh.myqcloud.com/%E6%9C%AA%E5%91%BD%E5%90%8D%E6%96%87%E4%BB%B6.jpg)


### NIO

暂时未测