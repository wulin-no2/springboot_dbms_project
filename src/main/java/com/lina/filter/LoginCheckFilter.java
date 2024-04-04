package com.lina.filter;

import com.google.gson.Gson;
import com.lina.config.JwtUtils;
import com.lina.pojo.Result;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1. get url
        String requestURL = request.getRequestURL().toString();
        // 2. if url includes login
        if (requestURL.contains("login")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 3. get token from request header
        String token = request.getHeader("token");
        // 4. if token is not exist, return error;
        if (! StringUtils.hasLength(token)){
            Result res = Result.error("NOT_LOGIN");
            Gson gson = new Gson();
            String json = gson.toJson(res);
            response.getWriter().write(json);
            return;
        }
        // 5. parse token and doFilter
        try {
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            e.printStackTrace();
            log.info("token parse fail.");
            // return error;
            Result res = Result.error("NOT_LOGIN");
            Gson gson = new Gson();
            String json = gson.toJson(res);
            response.getWriter().write(json);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
