package com.lina.interceptor;

import com.google.gson.Gson;
import com.lina.config.JwtUtils;
import com.lina.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. get url
        String requestURL = request.getRequestURL().toString();
        // 2. if url includes login
        if (requestURL.contains("login")){
            return true;
        }
        // 3. get token from request header
        String token = request.getHeader("token");
        // 4. if token is not exist, return error;
        if (! StringUtils.hasLength(token)){
            Result res = Result.error("NOT_LOGIN");
            Gson gson = new Gson();
            String json = gson.toJson(res);
            response.getWriter().write(json);
            return false;
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
            return false;
        }
        log.info("login successful.");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
