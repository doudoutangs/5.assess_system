package com.assess.modules.assess.service;

import com.assess.common.utils.PageUtils;
import com.assess.modules.assess.entity.AssessStaff;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface AssessStaffService extends IService<AssessStaff> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteBatch(Long[] ids);

    void update(AssessStaff record);

    List<AssessStaff> getByAssessId(Long assessId, Long userId);
}
