package com.shui.gulimall.ware.service.impl;

import com.shui.gulimall.ware.vo.SkuHasStockVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shui.common.utils.PageUtils;
import com.shui.common.utils.Query;

import com.shui.gulimall.ware.dao.WareSkuDao;
import com.shui.gulimall.ware.entity.WareSkuEntity;
import com.shui.gulimall.ware.service.WareSkuService;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new QueryWrapper<WareSkuEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds) {
        List<SkuHasStockVo> collect = skuIds.stream().map(sku -> {
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            long count = baseMapper.getSkuStock(sku);
            skuHasStockVo.setSkuId(sku);
            skuHasStockVo.setHasStock(count>0);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return collect;
    }

}