package com.lina.controller;

import com.lina.config.JwtUtils;
import com.lina.pojo.Emp;
import com.lina.pojo.Result;
import com.lina.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    EmpService empService;
    @PostMapping("/login")
    private Result login(@RequestBody Emp emp){
        log.info("login emp {}", emp);
        Emp e = empService.login(emp);

        // generate token
        if (e!=null){
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("name",e.getName());
            claims.put("username",e.getUsername());
            String s = JwtUtils.generateJwt(claims);
            return Result.success(s);
        }
        return Result.error("Wrong user information.");
    }

}
