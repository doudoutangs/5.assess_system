package com.assess.modules.assess.service;

import com.assess.common.utils.PageUtils;
import com.assess.modules.assess.entity.AssessItem;
import com.assess.modules.assess.entity.AssessSet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface AssessItemService extends IService<AssessItem> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteBatch(Long[] ids);

    void update(AssessItem record);
}
