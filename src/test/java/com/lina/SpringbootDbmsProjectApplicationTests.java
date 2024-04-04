package com.lina;

import com.lina.mapper.DeptMapper;
import com.lina.pojo.Dept;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class SpringbootDbmsProjectApplicationTests {
    @Autowired
    private DeptMapper deptMapper;

    @Test
    void testDeptList() {
        List<Dept> list = deptMapper.list();
        list.stream().forEach(System.out::println);
    }
    @Test
    void testDeptListById(){
        Dept dept = deptMapper.listById(1);
        System.out.println(dept);
    }
    @Test
    void testUuid(){
        for (int i= 0 ; i<=10;i++){
            String string = UUID.randomUUID().toString();
            System.out.println(string);
        }
    }
    @Test
    void testGenJwt(){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","kuluoluo");
        // Generate a secure key for HS256
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();
        System.out.println(jwt);

    }

}
