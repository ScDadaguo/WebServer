/*
 * Copyright: 2019 dingxiang-inc.com Inc. All rights reserved.
 */

package com.guohao.webserver.core.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @FileName: Header.java
 * @Description: Header.java类说明
 * @Author: guohao
 * @Date: 2019/8/2 11:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Header {
    private String key;
    private String value;
}
