package com.wsw.fusertaskmanager.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author WangSongWen
 * @Date: Created in 10:44 2020/11/13
 * @Description: 自定义JwtToken拦截器
 */
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("JwtTokenInterceptor.preHandle");
        return true;
    }
}
