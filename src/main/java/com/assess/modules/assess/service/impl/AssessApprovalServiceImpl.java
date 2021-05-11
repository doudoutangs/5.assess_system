/**
 *
 */

package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.dao.AssessApprovalDao;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.service.AssessApprovalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;


@Service
public class AssessApprovalServiceImpl extends ServiceImpl<AssessApprovalDao, AssessApproval> implements AssessApprovalService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long assessId = (Long)params.get("assessId");
        IPage<AssessApproval> page = this.page(
                new Query<AssessApproval>().getPage(params),
                new QueryWrapper<AssessApproval>()
                        .eq(assessId!=null,"assess_id",assessId)
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
    public void update(AssessApproval record) {
        this.updateById(record);
    }

    @Override
    public AssessApproval getByDeclareId(Long declareId) {
        return baseMapper.getByDeclareId(declareId);
    }
}
