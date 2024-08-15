package com.shop.interceptor;

import com.shop.utils.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 用來檢查token的攔截器
 */

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProvider jwtProvider;

    //handler預處理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if("".equals(token) || jwtProvider.isExpiration(token)){
            //token為空或過期，對該request進行攔截，並返回客戶端未登入
            return false;//攔截
        }
        return true;//放行
    }
}
