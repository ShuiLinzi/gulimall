package com.shui.gulimall.order.dao;

import com.shui.gulimall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:32:40
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {

}
