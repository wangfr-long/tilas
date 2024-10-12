package com.ithbu.tilas.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.ithbu.tilas.pojo.Result;
import com.ithbu.tilas.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init初始化完成了");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse)servletResponse;
        String url = request.getRequestURL().toString();
        log.info("获取请求路径，{}",url);
        if (url.contains("login")) {
            log.info("登录操作");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)){
            log.info("请求失败未登录");
            Result notLogin = Result.error("NOT_LOGIN");
            String jsonString = JSON.toJSONString(notLogin);
            response.getWriter().write(jsonString);
            return;
        }
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌信息错误或者失效");
            Result notLogin = Result.error("NOT_LOGIN");
            String jsonString = JSON.toJSONString(notLogin);
            response.getWriter().write(jsonString);
            return;
        }
        log.info("令牌，{}",token);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy结束方法完成了");
        Filter.super.destroy();
    }
}
