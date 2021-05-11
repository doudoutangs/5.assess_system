package com.assess.modules.assess.dao;

import com.assess.modules.assess.entity.AssessDeclare;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssessDeclareDao extends BaseMapper<AssessDeclare> {
    /**
     * 查询用户绩效考核
     */

    List<AssessDeclare> getUserRecore(AssessDeclare assessDeclare);
}