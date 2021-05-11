package com.assess.modules.assess.dao;

import com.assess.modules.assess.entity.AssessStatis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AssessStatisDao extends BaseMapper<AssessStatis> {
    List<AssessStatis> statis(Map<String, Object> params);

}