/**
 *
 */

package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.dao.AssessStaffDao;
import com.assess.modules.assess.entity.AssessSet;
import com.assess.modules.assess.entity.AssessStaff;
import com.assess.modules.assess.service.AssessSetService;
import com.assess.modules.assess.service.AssessStaffService;
import com.assess.modules.sys.entity.SysDept;
import com.assess.modules.sys.entity.SysDict;
import com.assess.modules.sys.entity.SysUser;
import com.assess.modules.sys.service.SysDeptService;
import com.assess.modules.sys.service.SysDictService;
import com.assess.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class AssessStaffServiceImpl extends ServiceImpl<AssessStaffDao, AssessStaff> implements AssessStaffService {

    @Autowired
    SysUserService userService;

    @Autowired
    AssessSetService setService;
    @Autowired
    SysDictService dictService;
    @Autowired
    private SysDeptService deptService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AssessStaff> page = this.page(
                new Query<AssessStaff>().getPage(params),
                new QueryWrapper<AssessStaff>()
                        .eq(StringUtils.isNotBlank((String)params.get("assessId")), "assess_id", (String)params.get("assessId"))
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
                        .orderByDesc("create_time")
        );
        List<AssessStaff> records = page.getRecords();
        List<AssessStaff> list =new ArrayList<AssessStaff>();
        for (AssessStaff a : records) {
//            AssessStaffVO a = new AssessStaffVO();
//            a.setId(r.getId());
            //查询考核信息
            Long assessId = a.getAssessId();
            AssessSet set = setService.getById(assessId);
            a.setAssessId(assessId);
            a.setAssessTitle(set.getTitle());
            //查询字典
            List<SysDict> types = dictService.getListByType("assess_staff_type");
            String type = a.getType();
            String typeName = dictService.getNameByType(type,types);
            a.setType(type);
            a.setTypeName(typeName);
            //查询用户信息
            Long userId = a.getUserId();
            SysUser user = userService.getById(userId);
            SysDept dept = deptService.getById(user.getDeptId());
            a.setUserId(userId);
            a.setUserName(user.getName());
            a.setDeptName(dept.getName());
            list.add(a);
        }
        page.setRecords(list);
        return new PageUtils(page);
    }


    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));

    }

    @Override
    public void update(AssessStaff record) {
        this.updateById(record);
    }

    @Override
    public List<AssessStaff> getByAssessId(Long assessId, Long userId) {
        return baseMapper.getByAssessId(assessId,userId);
    }
}
