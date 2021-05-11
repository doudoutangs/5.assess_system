/**
 *
 */

package com.assess.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.assess.modules.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDept> queryList(Map<String, Object> map);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);

    List<SysDept> queryByDetpId(Long parentId);

    /**
     * 获取子部门ID，用于数据过滤
     */
    List<Long> getSubDeptIdList(Long deptId);

}
