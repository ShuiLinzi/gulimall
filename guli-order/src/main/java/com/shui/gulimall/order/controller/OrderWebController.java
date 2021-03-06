package com.shui.gulimall.order.controller;

import com.shui.gulimall.order.service.OrderService;
import com.shui.gulimall.order.vo.OrderConfirmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderWebController {
    @Autowired
    OrderService orderService;
    @GetMapping("/toTrade")
    public String toTrade(Model model){
      OrderConfirmVo confirmVo = orderService.confirmOrber();
      model.addAttribute("orderConfirmData",confirmVo);
        //展示订单确认的数据
        return "confirm";
    }
}
