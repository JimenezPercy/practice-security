package com.example.practice.security.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
public class ValidateCode {

    /**
     * 随机数，用于验证输入的验证码
     */
    private String code;

    /**
     * 过期时间点
     */
    private LocalDateTime expireTime;

    /**
     *
     * @param code 验证码
     * @param expireIn 过期时间(秒)，用于生成过期的时间点
     */
    public ValidateCode(String code, Integer expireIn){
        this.code=code;
        this.expireTime=LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 是否过期
     * @return
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
