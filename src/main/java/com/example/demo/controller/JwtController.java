package com.example.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.domain.Result;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.*;

/**
 * @author 你的名字
 * @date 2024/5/27
 * @description
 */

@RestController
@RequestMapping("/jwt/token/")
public class JwtController {

    private static String securityKey = "abcdefg";

    @PostMapping("/get")
    public String genToken() {
        Map<String,Object> header = new HashMap<String,Object>();


        header.put("alg","HS256");
        header.put("typ","JWT");

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        calendar.add(Calendar.MINUTE,1);
        System.out.println(calendar.getTime());



        String token = JWT.create().withHeader(header).withClaim("name","zhangsan")
                .withClaim("age",10).withSubject("test").withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(securityKey));

        System.out.println("token=" + token);


        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    @GetMapping("/verify")
    public Result verifyToken(@RequestParam String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(securityKey)).build();

        DecodedJWT verify = null;
        try {
            verify = jwtVerifier.verify(token);

        } catch (TokenExpiredException e) {
            System.out.println(e);
            // 传入的JWT字符串为空或不是预期格式
            // 可以提示用户检查输入
        }

        Map<String, Claim> claims = verify.getClaims();

        System.out.println("claims=" + claims);

        return new Result(claims.get("name").asString(),claims.get("age").asInt().toString());
    }

}
