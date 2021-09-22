package com.shui.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shui.common.utils.PageUtils;
import com.shui.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-29 23:17:09
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void updateCascade(CategoryEntity category);

    List<CategoryEntity> getLevel1Categories();

    //  void removeMenuByIds(List<Long> asList);
}

