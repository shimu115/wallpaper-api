//package com.shimu.ramdomimg.component;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class RateLimitInterceptor implements HandlerInterceptor {
//
//    private final RateLimitManager manager;
//
//    public RateLimitInterceptor(RateLimitManager manager) {
//        this.manager = manager;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String path = request.getRequestURI();
//        if (!manager.tryAcquire(path)) {
//            response.setStatus(429);
//            response.getWriter().write("请求过于频繁，请稍后再试！");
//            return false;
//        }
//        return true;
//    }
//}
