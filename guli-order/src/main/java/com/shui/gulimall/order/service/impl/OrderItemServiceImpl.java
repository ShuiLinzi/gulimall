package com.shui.gulimall.order.service.impl;

import com.rabbitmq.client.Channel;
import com.shui.gulimall.order.entity.OrderReturnApplyEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shui.common.utils.PageUtils;
import com.shui.common.utils.Query;

import com.shui.gulimall.order.dao.OrderItemDao;
import com.shui.gulimall.order.entity.OrderItemEntity;
import com.shui.gulimall.order.service.OrderItemService;


@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(
                new Query<OrderItemEntity>().getPage(params),
                new QueryWrapper<OrderItemEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * queues指监听的所有的队列
     * 参数可以写以下类型
     * Message message原生消息详细信息，包含消息头+体
     * T<> 发送消息的类型
     * Channel 传输消息的队列
     */
    @RabbitListener(queues = "hello-java-queue")
    public void recieveMessage(Message message, OrderReturnApplyEntity entity, Channel channel){

        System.out.println("接收到消息"+message);

    }
}