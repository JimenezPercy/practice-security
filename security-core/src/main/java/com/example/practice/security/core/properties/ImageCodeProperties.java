package com.example.practice.security.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties extends SmsCodeProperties {

    /**
     * 图形验证码宽度
     *
     * 默认配置：67
     */
    private int width = 67;

    /**
     * 图形验证码高度
     *
     * 默认配置：23
     */
    private int height = 23;

    public ImageCodeProperties(){
        this.setLength(6);
    }
}
