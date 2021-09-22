package com.shui.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:54:04
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

