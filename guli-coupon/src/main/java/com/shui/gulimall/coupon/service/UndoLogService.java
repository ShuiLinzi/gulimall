package com.shui.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.coupon.entity.UndoLogEntity;

import java.util.Map;

/**
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:24:32
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

