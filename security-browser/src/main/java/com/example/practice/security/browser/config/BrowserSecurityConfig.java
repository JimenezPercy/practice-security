package com.example.practice.security.browser.config;

import com.example.practice.security.core.authentication.AbstractChannelSecurityConfig;
import com.example.practice.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.example.practice.security.core.constants.SecurityConstants;
import com.example.practice.security.core.properties.SecurityProperties;
import com.example.practice.security.core.validate.code.ValidateCodeSecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    private final SecurityProperties securityProperties;

    private final ValidateCodeSecurityConfig validateCodeSecurityConfig;

    private final SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    private final UserDetailsService userDetailsService;

    private final PersistentTokenRepository persistentTokenRepository;

    private final SpringSocialConfigurer springSocialConfigurer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);

        http.rememberMe()
                    .tokenRepository(persistentTokenRepository)
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()
                    .antMatchers(
                            SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                            securityProperties.getBrowser().getLoginPage())
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                .csrf()
                    .disable()
                .apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(springSocialConfigurer);
    }
}
