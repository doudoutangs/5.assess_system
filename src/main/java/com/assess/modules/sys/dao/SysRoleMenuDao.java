/**
 * 
 *
 * 
 *
 * 
 */

package com.assess.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.assess.modules.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色与菜单对应关系
 *
 * @author 
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
