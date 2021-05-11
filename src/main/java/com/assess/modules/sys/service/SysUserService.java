/**
 *
 */

package com.assess.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.assess.common.utils.PageUtils;
import com.assess.modules.sys.entity.SysUser;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author
 */
public interface SysUserService extends IService<SysUser> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 查询部门下的所有人
     * @param deptId
     * @return
     */
    List<SysUser> queryByDeptId(Long deptId);

    List<SysUser> queryByNoDeptId(Long deptId);

    /**
     * 保存用户
     */
    void saveUser(SysUser user);

    /**
     * 修改用户
     */
    void update(SysUser user);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
