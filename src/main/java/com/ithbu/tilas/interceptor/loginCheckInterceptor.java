package com.ithbu.tilas.interceptor;

import com.alibaba.fastjson.JSON;
import com.ithbu.tilas.pojo.Result;
import com.ithbu.tilas.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class loginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = request.getHeader("token");
        if (!StringUtils.hasLength(jwt)){
            log.info("无令牌，请登录");
            Result notLogin = Result.error("NOT_LOGIN");
            String s = JSON.toJSONString(notLogin);
            response.getWriter().write(s);
            return false;
        }
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("令牌错误或失效");
            Result notLogin = Result.error("NOT_LOGIN");
            String s = JSON.toJSONString(notLogin);
            response.getWriter().write(s);
            return false;
        }
        log.info("登录成功，令牌{}",jwt);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle---");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("aftercompletion---");
    }
}
