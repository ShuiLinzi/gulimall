package com.shui.gulimall.ware.dao;

import com.shui.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * εεεΊε­
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:54:04
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    long getSkuStock(@Param("sku") Long sku);
}
