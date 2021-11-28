package com.example.practice.demo.controller;

import com.example.practice.demo.async.DeferredResultHolder;
import com.example.practice.demo.async.MockQueue;
import com.example.practice.demo.dto.User;
import com.example.practice.demo.dto.UserQueryCondition;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class UserController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/user")
    public UserDetails query(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @JsonView(User.BasicView.class)
    @PostMapping("/user")
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(err -> {
                System.out.println(err.getDefaultMessage());
            });
        }
        return user;
    }

    @GetMapping("/test")
    public Callable<String> test() {
        Callable<String> result = new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "success";
            }
        };
        return result;
    }

    @PostMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        String orderNumber= RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result=new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);
        return result;
    }
}
