/**
 *
 */

package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.dao.AssessItemDao;
import com.assess.modules.assess.dao.AssessSetDao;
import com.assess.modules.assess.entity.AssessItem;
import com.assess.modules.assess.entity.AssessSet;
import com.assess.modules.assess.service.AssessSetService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AssessSetServiceImpl extends ServiceImpl<AssessSetDao, AssessSet> implements AssessSetService {
    @Autowired
    AssessItemDao assessItemDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("title");
        IPage<AssessSet> page = this.page(
                new Query<AssessSet>().getPage(params),
                new QueryWrapper<AssessSet>()
                        .like(StringUtils.isNotBlank(name), "title", name)
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
    public void update(AssessSet record) {
        this.updateById(record);
    }

    @Override
    public AssessSet get(Long id) {
        AssessSet set = this.getById(id);
        List<AssessItem> assessItems = assessItemDao.queryItemByAssessId(set.getId());
        set.setItems(assessItems);
        return set;
    }
}
