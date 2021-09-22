package com.shui.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:54:04
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

