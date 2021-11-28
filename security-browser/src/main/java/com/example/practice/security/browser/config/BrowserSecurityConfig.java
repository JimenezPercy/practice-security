package com.example.practice.security.browser.config;

import com.example.practice.security.browser.authentication.PracticeAuthenticationFailureHandler;
import com.example.practice.security.browser.authentication.PracticeAuthenticationSuccessHandler;
import com.example.practice.security.browser.service.MyUserDetailService;
import com.example.practice.security.core.authentication.mobile.SmsCodeAuthenticationFilter;
import com.example.practice.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.example.practice.security.core.properties.SecurityProperties;
import com.example.practice.security.core.validate.code.ValidateCodeFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;
    private final PracticeAuthenticationSuccessHandler practiceAuthenticationSuccessHandler;
    private final PracticeAuthenticationFailureHandler practiceAuthenticationFailureHandler;
    //验证码过滤器
    private final ValidateCodeFilter validateCodeFilter;

    private final SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    private final UserDetailsService userDetailsService;

    private final PersistentTokenRepository persistentTokenRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .successHandler(practiceAuthenticationSuccessHandler)
                .failureHandler(practiceAuthenticationFailureHandler)
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        "/code/image",
                        "/code/sms",
                        "/code/*",
                        securityProperties.getBrowser().getLoginPage())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}
