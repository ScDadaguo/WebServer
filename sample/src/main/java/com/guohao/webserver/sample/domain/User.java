package com.guohao.webserver.sample.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guohao
 * @date 2019/5/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String realName;
    private Integer age;
}
