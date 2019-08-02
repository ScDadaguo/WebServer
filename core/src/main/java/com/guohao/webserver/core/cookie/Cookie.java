/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.cookie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName: Cookie.java
 * @Description: Cookie.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cookie {
    private String key;
    private String value;
}

