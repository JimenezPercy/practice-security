package com.example.practice.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全配置参数类
 *
 * SecurityProperties
 * |-BrowserProperties
 * |-ValidateCodeProperties
 * | |-ImageCodeProperties
 */
@ConfigurationProperties(prefix = "practice.security")
@Data
public class SecurityProperties {

    /**
     * 浏览器配置
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码配置
     */
    private ValidateCodeProperties code=new ValidateCodeProperties();

    /**
     * 社交配置
     */
    private SocialProperties social=new SocialProperties();
}
