package com.example.practice.security.core.social.qq.config;

import com.example.practice.security.core.properties.QQProperties;
import com.example.practice.security.core.properties.SecurityProperties;
import com.example.practice.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@ConditionalOnProperty(prefix = "practice.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq=securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qq.getProviderId(),qq.getAppId(),qq.getAppSecret());
    }
}
