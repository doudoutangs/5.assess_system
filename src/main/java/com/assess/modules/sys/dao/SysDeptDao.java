package com.assess.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.assess.modules.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDept> {

    List<SysDept> queryList(Map<String, Object> params);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
    List<SysDept> queryByDetpId(Long parentId);

}
