package com.shui.gulimall.product.dao;

import com.shui.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-29 23:17:09
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchRelation(@Param("collect") List<AttrAttrgroupRelationEntity> collect);

}
