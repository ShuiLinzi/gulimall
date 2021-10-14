package com.shui.gulimall.order;

import com.shui.gulimall.order.entity.OrderOperateHistoryEntity;
import com.shui.gulimall.order.entity.OrderReturnReasonEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@Slf4j
@SpringBootTest
class GuliOrderApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;//用来发送接受rabbitmq消息的
    @Test
    void contextLoads() {
    }
    @Test
    public void sendMessageTest(){
        String msg = "hello world";
        //测试发送一个对象
        OrderReturnReasonEntity operateHistoryEntity = new OrderReturnReasonEntity();
        operateHistoryEntity.setId(1L);
        operateHistoryEntity.setCreateTime(new Date());
        operateHistoryEntity.setName("2333");
        //发送对象

        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java",operateHistoryEntity);
    }
    @Test
    void createExchange(){
        //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
        //源码中DirectExchange里面的所有参数

        DirectExchange directExchange = new DirectExchange("hello-java-exchange",true,true);
        log.info("创建成功{}","hello-java-exchange");
        amqpAdmin.declareExchange(directExchange);
    }

    @Test
    void createQueue(){
        //String name, boolean durable, boolean exclusive, boolean autoDelete,
        //			@Nullable Map<String, Object> arguments
        //queue参数最多的构造器
        Queue queue = new Queue("hello-java-queue",true,false,false);
        amqpAdmin.declareQueue(queue);

    }
    @Test
    void createBinding(){
        //Binding的全参构造器的解释
        //(String destination[目的地],
        // DestinationType destinationType,【目的地类型】
        // String exchange,【交换机】
        // String routingKey【路由键】,
        // @Nullable Map<String, Object> arguments【自定义参数】
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE,"hello-java-exchange","hello.java",null);

        amqpAdmin.declareBinding(binding);
    }
}
