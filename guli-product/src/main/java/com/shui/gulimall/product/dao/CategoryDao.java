package com.shui.gulimall.product.dao;

import com.shui.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-29 23:17:09
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {

}
