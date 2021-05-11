package com.assess.modules.assess.service;

import com.assess.common.utils.PageUtils;
import com.assess.modules.assess.entity.AssessAndItem;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.entity.AssessItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author
 */
public interface AssessAndItemService extends IService<AssessAndItem> {

    PageUtils queryPage(Map<String, Object> params);

    void deleteBatch(Long[] ids);

    void update(AssessAndItem record);
    List<AssessAndItem> queryByAssessId(Long assessId);

    List<AssessAndItem> queryByItemId(Long itemId);


}
