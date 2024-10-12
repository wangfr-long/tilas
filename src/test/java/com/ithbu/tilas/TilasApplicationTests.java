package com.ithbu.tilas;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TilasApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    public void jwtTest(){
        Map<String, Object> Claims=new HashMap<>();
        Claims.put("id","ithbu");
        Claims.put("name","wfw");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "ithbu")
                .setClaims(Claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }
    @Test
    public void jwtParseTest(){
        Map<String, Object> body = Jwts.parser()
                .setSigningKey("ithbu")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoid2Z3IiwiaWQiOiJpdGhidSIsImV4cCI6MTcyODQ0MDc4NX0.P54YkPvtgj81AfrsTo6Lw1JXWIJxFu2YeMTBoQL3apE")
                .getBody();
        System.out.println(body);
    }
}
