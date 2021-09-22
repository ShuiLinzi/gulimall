package com.shui.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.order.entity.UndoLogEntity;

import java.util.Map;

/**
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:32:40
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

