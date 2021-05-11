package com.assess.modules.assess.service;

import com.assess.common.utils.PageUtils;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.entity.AssessStatis;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface AssessStatisService extends IService<AssessStatis> {
    List statis();
}
