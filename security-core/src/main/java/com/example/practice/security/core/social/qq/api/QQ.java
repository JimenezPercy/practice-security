package com.example.practice.security.core.social.qq.api;

import java.io.IOException;

public interface QQ {
    /**
     * 获取用户信息
     *
     * @return
     */
    QQUserInfo getUserInfo() throws IOException;
}
