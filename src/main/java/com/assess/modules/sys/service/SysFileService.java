package com.assess.modules.sys.service;

import com.assess.modules.sys.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 数据字典
 *
 * @author
 */
public interface SysFileService extends IService<SysFile> {
    List<SysFile> queryByRelationId(String relationId);
}

