package com.shui.gulimall.member.dao;

import com.shui.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 *
 * @author lin
 * @email shuilinzi@qq.com
 * @date 2021-08-30 11:02:59
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {

}
