package com.example.practice.security.core.validate.code.image;

import com.example.practice.security.core.validate.code.ValidateCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class ImageCode extends ValidateCode {

    /**
     * 图片
     */
    private BufferedImage image;

    /**
     *
     * @param image 图片
     * @param code 验证码
     * @param expireIn 过期时间(秒)，用于生成过期的时间点
     */
    public ImageCode(BufferedImage image,String code,Integer expireIn){
        super(code,expireIn);
        this.image=image;
    }
}
