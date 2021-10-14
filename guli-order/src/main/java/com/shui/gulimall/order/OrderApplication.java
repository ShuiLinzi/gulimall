package com.shui.gulimall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用rabbit mq
 * 1.引用amqp场景 rabbitAutoConfiguration就会自动生效
 * 2.给容器中自动配置了 RabbitTemplate AmqpAdmin
 *      所有属性都是spring.rabbitmq
 *       详细看源码中的
 *       @ConfigurationProperties(prefix = "spring.rabbitmq")
 *       所有的配置信息都在配置文件中
 * 3.EnableRabbit ,在spring中 开启某个中间件的功能，一般用@EnableXxxx来开启
 *
 */
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
