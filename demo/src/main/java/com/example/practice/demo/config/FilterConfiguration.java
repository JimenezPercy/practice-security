package com.example.practice.demo.config;

import com.example.practice.demo.filter.TimeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
public class FilterConfiguration {

    @Autowired
    public TimeFilter timeFilter;
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(timeFilter);
        bean.setUrlPatterns(Arrays.asList("/user"));
        return bean;
    }
}
