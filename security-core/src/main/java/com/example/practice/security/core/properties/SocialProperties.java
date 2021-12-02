package com.example.practice.security.core.properties;

import lombok.Data;

@Data
public class SocialProperties {
    /**
     * 社交登录过滤器拦截请求地址前缀
     */
    private String filterProcessesUrl = "/auth";


    private QQProperties qq = new QQProperties();
}
