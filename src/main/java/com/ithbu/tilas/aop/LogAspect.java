package com.ithbu.tilas.aop;

import com.alibaba.fastjson.JSON;
import com.ithbu.tilas.mapper.OperateLogMapper;
import com.ithbu.tilas.pojo.OperateLog;
import com.ithbu.tilas.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(com.ithbu.tilas.anno.Log)")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
//        获取属性值
        String name = proceedingJoinPoint.getTarget().getClass().getName();
        Signature signature = proceedingJoinPoint.getSignature();
        String method = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
//        执行函数
        Object o = proceedingJoinPoint.proceed();
        String jsonString = JSON.toJSONString(o);
        long end = System.currentTimeMillis();
//        设置属性值
        String token = request.getHeader("token");
        Map<String, Object> claims = JwtUtils.parseJWT(token);
        Integer o1 = (Integer) claims.get("id");//获取操作人，也就是登录人id
        OperateLog operateLog = new OperateLog(null,o1,
                LocalDateTime.now(),name,
                method,Arrays.toString(args),jsonString,
                end-start);
        operateLogMapper.insert(operateLog);
        log.info(proceedingJoinPoint.getSignature() + "执行耗时{}ms", end - start);
        return o;
    }
}
