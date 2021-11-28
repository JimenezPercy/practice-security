package com.example.practice.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("prehandler...");
        System.out.println(((HandlerMethod)o).getMethod().getName());
        httpServletRequest.setAttribute("starttime",new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("posthandler...");
        Long starttime = (Long) httpServletRequest.getAttribute("starttime");

        System.out.println("post "+(new Date().getTime()-starttime));
//        ((HandlerMethod)o).getMethod().
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterhandler...");
        Long starttime = (Long) httpServletRequest.getAttribute("starttime");
        System.out.println("after "+(new Date().getTime()-starttime));
    }
}
