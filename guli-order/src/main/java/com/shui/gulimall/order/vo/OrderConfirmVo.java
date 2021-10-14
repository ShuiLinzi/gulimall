package com.shui.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
//订单确认页需要用到的数据
public class OrderConfirmVo {
    //收获地址
    List<MemberAddressVo> memberAddress;
    //y优惠券信息
    Integer integeration;

    BigDecimal total;//订单总额
    BigDecimal payPrice; //应付价格


}
