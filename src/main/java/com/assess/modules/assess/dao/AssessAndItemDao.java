package com.assess.modules.assess.dao;

import com.assess.modules.assess.entity.AssessAndItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssessAndItemDao extends BaseMapper<AssessAndItem> {
    List<AssessAndItem> queryByAssessId(Long assessId);
    List<AssessAndItem> queryByItemId(Long itemId);
}