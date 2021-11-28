package com.example.practice.security.browser.config;

import com.example.practice.security.browser.service.MyUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class CommonConfig {

    private final DataSource dataSource;

    @Bean
    public UserDetailsService myUserDetailService() {
        MyUserDetailService myUserDetailService = new MyUserDetailService();
        myUserDetailService.setPasswordEncoder(passwordEncoder());
        return myUserDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //初始化时创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
