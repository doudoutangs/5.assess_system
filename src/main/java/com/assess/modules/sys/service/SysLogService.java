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
import com.assess.modules.sys.entity.SysLog;

import java.util.Map;


/**
 * 系统日志
 *
 * @author
 */
public interface SysLogService extends IService<SysLog> {

    PageUtils queryPage(Map<String, Object> params);

}
