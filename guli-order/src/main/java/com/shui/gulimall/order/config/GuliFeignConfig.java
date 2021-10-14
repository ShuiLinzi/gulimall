package com.shui.gulimall.order.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

//RequestInterceptor  feign调用过程中拦截器的接口 加上feign远程调用的请求拦截器，然后给新的请求体
//                      加上，就解决了Feign远程调用丢失请求头的问题
@Configuration
public class GuliFeignConfig {
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request =  attributes.getRequest();//老请求
                String cookie = request.getHeader("Cookie");
                //给新请求同步了老请求的cookie
                requestTemplate.header("Cookie",cookie);
            }
        };
    }
}
