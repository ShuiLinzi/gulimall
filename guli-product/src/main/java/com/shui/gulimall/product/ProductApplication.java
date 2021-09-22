package com.shui.gulimall.product;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 逻辑删除
 * 1.在配置文件中配置逻辑删除规则，默认0是未被删除，1是已删除（可以省略）
 * 2.配置逻辑删除的组件bean（可以省略）
 * 3.给实体类中表示逻辑删除状态的字段上面加上一个注解 @TableLogic
 * <p>
 * <p>
 * 统一异常处理类
 *
 * @controlleradvirce
 */

@MapperScan("com.shui.gulimall.product.dao")
@SpringBootApplication
@EnableFeignClients
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
