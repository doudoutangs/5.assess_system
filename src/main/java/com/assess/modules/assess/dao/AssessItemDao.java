package com.assess.modules.assess.dao;

import com.assess.modules.assess.entity.AssessItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssessItemDao extends BaseMapper<AssessItem> {
    List<AssessItem> queryItemByAssessId(Long assessId);
}