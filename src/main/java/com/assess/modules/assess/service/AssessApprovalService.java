package com.assess.modules.assess.service;

import com.assess.common.utils.PageUtils;
import com.assess.modules.assess.entity.AssessApproval;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * 数据字典
 *
 * @author 
 */
public interface AssessApprovalService extends IService<AssessApproval> {

    PageUtils queryPage(Map<String, Object> params);
    void deleteBatch(Long[] ids);

    void update(AssessApproval record);
    AssessApproval getByDeclareId(Long declareId);

}
