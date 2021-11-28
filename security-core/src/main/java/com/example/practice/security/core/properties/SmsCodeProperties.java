package com.example.practice.security.core.properties;

import lombok.Data;

@Data
public class SmsCodeProperties {

    /**
     * 验证码字符串长度
     *
     * 默认配置：6
     */
    private int length = 6;

    /**
     * 短信验证码过期时间
     *
     * 默认配置：60s
     */
    private int expireIn = 60;

    /**
     * 短信验证拦截地址，以逗号分隔的多个请求路径
     */
    private String url;
}
