package com.ithbu.tilas.controller;

import com.ithbu.tilas.pojo.Emp;
import com.ithbu.tilas.pojo.Result;
import com.ithbu.tilas.service.EmpService;
import com.ithbu.tilas.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录,{}",emp);
        Emp login = empService.login(emp);
        if (login!=null){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",login.getId());
            claims.put("password",login.getPassword());
            claims.put("username",login.getUsername());
            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }else return Result.error("NOT_LOGIN");
    }
}
