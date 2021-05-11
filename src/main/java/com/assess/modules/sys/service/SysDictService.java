/**
 *
 */

package com.assess.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.assess.common.utils.PageUtils;
import com.assess.modules.sys.entity.SysDict;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface SysDictService extends IService<SysDict> {

    PageUtils queryPage(Map<String, Object> params);

    List<SysDict> getListByType(String type);

    String getNameByType(String code,List<SysDict> list);
}

