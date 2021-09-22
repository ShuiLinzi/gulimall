package com.shui.gulimall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.validation.groups.Default;

@EnableDiscoveryClient//开启服务的注册发现
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GuliGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliGatewayApplication.class, args);
    }

}
