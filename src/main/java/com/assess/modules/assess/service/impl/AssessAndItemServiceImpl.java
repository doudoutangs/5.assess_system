/**
 *
 */

package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.dao.AssessAndItemDao;
import com.assess.modules.assess.dao.AssessApprovalDao;
import com.assess.modules.assess.entity.AssessAndItem;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.service.AssessAndItemService;
import com.assess.modules.assess.service.AssessApprovalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AssessAndItemServiceImpl extends ServiceImpl<AssessAndItemDao, AssessAndItem> implements AssessAndItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");
        IPage<AssessAndItem> page = this.page(
                new Query<AssessAndItem>().getPage(params),
                new QueryWrapper<AssessAndItem>()
                        .like(StringUtils.isNotBlank(name), "name", name)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
                        .orderByDesc("create_time")
        );
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));

    }

    @Override
    public void update(AssessAndItem record) {
        this.updateById(record);
    }

    @Override
    public List<AssessAndItem> queryByAssessId(Long assessId) {
        return baseMapper.queryByAssessId(assessId);
    }

    @Override
    public List<AssessAndItem> queryByItemId(Long itemId) {
        return baseMapper.queryByItemId(itemId);
    }
}
