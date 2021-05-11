package com.assess.modules.assess.dao;

import com.assess.modules.assess.entity.AssessStaff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssessStaffDao extends BaseMapper<AssessStaff> {
    List<AssessStaff> getByAssessId(@Param("assessId")Long assessId, @Param("userId") Long userId);
}