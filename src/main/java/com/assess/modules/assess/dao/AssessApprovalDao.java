package com.assess.modules.assess.dao;

import com.assess.modules.assess.entity.AssessApproval;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssessApprovalDao extends BaseMapper<AssessApproval> {

    AssessApproval getByDeclareId(Long declareId);
}