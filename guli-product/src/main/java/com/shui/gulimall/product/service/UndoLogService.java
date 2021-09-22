package com.shui.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.product.entity.UndoLogEntity;

import java.util.Map;

/**
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-29 23:17:09
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

