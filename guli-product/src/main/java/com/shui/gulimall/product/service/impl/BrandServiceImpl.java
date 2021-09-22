package com.shui.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shui.common.utils.PageUtils;
import com.shui.common.utils.Query;

import com.shui.gulimall.product.dao.BrandDao;
import com.shui.gulimall.product.entity.BrandEntity;
import com.shui.gulimall.product.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {
    @Autowired
    CategoryBrandRelationServiceImpl brandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        //获得参数key
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("brand_id", key).or().like("name", key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void updateDetail(BrandEntity brand) {
        //为了保证数据表中的冗余数据一致
        baseMapper.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            brandRelationService.updataBrand(brand.getBrandId(), brand.getName());

            //todo 更新其他的关联

        }
    }

}