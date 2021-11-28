package com.example.practice.security.core.properties;

import com.example.practice.security.core.enums.LoginType;
import lombok.Data;

@Data
public class BrowserProperties {

    /**
     * 登录页
     */
    private String loginPage="/login.html";

    /**
     * 登录处理类型
     */
    private LoginType loginType=LoginType.JSON;

    /**
     * 记住我的时长
     */
    private int rememberMeSeconds=3600;
}
