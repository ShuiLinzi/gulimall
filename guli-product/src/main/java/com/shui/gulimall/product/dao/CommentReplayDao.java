package com.shui.gulimall.product.dao;

import com.shui.gulimall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-29 23:17:09
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {

}
