package com.shui.gulimall.order.interceptor;

import com.shui.gulimall.order.entity.OrderEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * //spring 1.拦截器,要实现HandlerInterceptor的接口
 *          2.放入容器中 @Component
 *          3.在配置类中配置
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {
    public static ThreadLocal<OrderEntity> loginUser = new ThreadLocal();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        OrderEntity attribute = (OrderEntity) request.getSession().getAttribute("loginUser");
        if (attribute!=null){
            //登录状态
            loginUser.set(attribute);
        }else {
            //未登录状态
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
