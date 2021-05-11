package com.assess.modules.assess.service;

import com.assess.common.utils.PageUtils;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.entity.AssessDeclare;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 数据字典
 *
 * @author 
 */
public interface AssessDeclareService extends IService<AssessDeclare> {

    PageUtils queryPage(Map<String, Object> params);
    void deleteBatch(Long[] ids);

    void update(AssessDeclare record);
    AssessDeclare get(Long id);
}
