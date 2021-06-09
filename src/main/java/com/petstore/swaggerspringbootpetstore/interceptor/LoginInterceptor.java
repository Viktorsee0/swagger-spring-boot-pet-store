package com.petstore.swaggerspringbootpetstore.interceptor;

import com.petstore.swaggerspringbootpetstore.until.CreateToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("X-Expires-After") != null &&
                request.getHeader("X-Rate-Limit") != null) {
            return false;
        }

        response.setHeader("X-Expires-After", CreateToken.createTime());
        response.setIntHeader("X-Rate-Limit", CreateToken.createCalls());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}