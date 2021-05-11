/**
 * 
 *
 * 
 *
 * 
 */

package com.assess.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.assess.common.utils.PageUtils;
import com.assess.modules.sys.entity.SysRole;

import java.util.Map;


/**
 * 角色
 *
 * @author 
 */
public interface SysRoleService extends IService<SysRole> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRole role);

	void update(SysRole role);
	
	void deleteBatch(Long[] roleIds);

}
