package com.example.practice.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器接口
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
