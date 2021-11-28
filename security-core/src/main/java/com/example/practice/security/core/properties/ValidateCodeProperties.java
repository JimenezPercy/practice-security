package com.example.practice.security.core.properties;

import lombok.Data;

/**
 * 验证码配置参数
 */
@Data
public class ValidateCodeProperties {
    /**
     * 图形验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
