/**
 *
 */

package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.dao.AssessDeclareDao;
import com.assess.modules.assess.dao.AssessItemDao;
import com.assess.modules.assess.entity.AssessDeclare;
import com.assess.modules.assess.entity.AssessItem;
import com.assess.modules.assess.service.AssessDeclareService;
import com.assess.modules.assess.service.AssessItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AssessItemServiceImpl extends ServiceImpl<AssessItemDao, AssessItem> implements AssessItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage<AssessItem> page = this.page(
                new Query<AssessItem>().getPage(params),
                new QueryWrapper<AssessItem>()
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
    public void update(AssessItem record) {
        this.updateById(record);
    }

}
