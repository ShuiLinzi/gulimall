package com.shui.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.ware.entity.WareSkuEntity;
import com.shui.gulimall.ware.vo.SkuHasStockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:54:04
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);
}

