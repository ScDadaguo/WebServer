package com.guohao.webserver.core.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @FileName: XMLUtil.java
 * @Description: XMLUtil.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 14:34
 */
public class XMLUtil {
    
    public static Document getDocument(InputStream in) {
        try {
            SAXReader reader = new SAXReader();
            return reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
