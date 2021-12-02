package com.example.practice.security.core.social;

import com.example.practice.security.core.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@AllArgsConstructor
public class SocialConfig extends SocialConfigurerAdapter {
    private final DataSource dataSource;
    private final SecurityProperties securityProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
        return new PracticeSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
    }
}
