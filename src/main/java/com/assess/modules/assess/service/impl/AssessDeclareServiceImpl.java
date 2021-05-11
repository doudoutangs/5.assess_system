package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.constant.DeclareTypeEnum;
import com.assess.modules.assess.dao.AssessApprovalDao;
import com.assess.modules.assess.dao.AssessDeclareDao;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.entity.AssessDeclare;
import com.assess.modules.assess.entity.AssessSet;
import com.assess.modules.assess.service.AssessDeclareService;
import com.assess.modules.assess.service.AssessItemService;
import com.assess.modules.assess.service.AssessSetService;
import com.assess.modules.sys.dao.SysFileDao;
import com.assess.modules.sys.entity.SysDept;
import com.assess.modules.sys.entity.SysFile;
import com.assess.modules.sys.entity.SysUser;
import com.assess.modules.sys.service.SysDeptService;
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
public class AssessDeclareServiceImpl extends ServiceImpl<AssessDeclareDao, AssessDeclare> implements AssessDeclareService {

    @Autowired
    SysUserService userService;
    @Autowired
    SysDeptService deptService;

    @Autowired
    AssessDeclareDao declareDao;

    @Autowired
    AssessApprovalDao approvalDao;
    @Autowired
    SysFileDao sysFileDao;

    @Autowired
    AssessSetService setService;
    @Autowired
    AssessItemService itemService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Long userId = (Long)params.get("userId");
        String assessId = (String)params.get("assessId");
        String statusStr = (String)params.get("status");
        String[] status = StringUtils.isNotBlank(statusStr)?statusStr.split(","):null;
        IPage<AssessDeclare> page = this.page(
                new Query<AssessDeclare>().getPage(params),
                new QueryWrapper<AssessDeclare>()
                        .eq("type",DeclareTypeEnum.WHOLE.getCode())
                        .eq(userId!=null,"user_id",userId)
                        .eq(StringUtils.isNotBlank(assessId),"assess_id",assessId)
                        .in(StringUtils.isNotBlank(statusStr),"status",status)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String) params.get(Constant.SQL_FILTER))
                        .orderByDesc("create_time")
        );
        List<AssessDeclare> records = page.getRecords();
        List<AssessDeclare> list =new ArrayList<AssessDeclare>();
        for (AssessDeclare a : records) {
            setInfo(a);
            list.add(a);
        }
        page.setRecords(list);
        page.setTotal(page.getTotal());
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        this.removeByIds(Arrays.asList(ids));

    }

    @Override
    public void update(AssessDeclare record) {
        this.updateById(record);
    }

    @Override
    public AssessDeclare get(Long id) {
        AssessDeclare declare = this.getById(id);
        setInfo(declare);
        return declare;
    }

    private void setInfo(AssessDeclare declare) {
        Long assessId = declare.getAssessId();
        Long userId = declare.getUserId();
        //1.查询考核信息
        AssessSet set = setService.get(assessId);
        //2.用户信息
        SysUser user = userService.getById(userId);
        SysDept dept = deptService.getById(user.getDeptId());
        user.setDeptName(dept.getName());
        declare.setAssessSet(set);
        declare.setUser(user);
        declare.setItems(set.getItems());
        AssessDeclare d = new AssessDeclare();
        d.setAssessId(assessId);
        d.setUserId(userId);
        d.setType(DeclareTypeEnum.INDIVIDUAL.getCode());
        //3.查询自评项信息
        List<AssessDeclare> records = declareDao.getUserRecore(d);
        declare.setDeclareList(records);
        //4.查询审批信息
        AssessApproval approval = approvalDao.getByDeclareId(declare.getId());
        declare.setApproval(approval);
        //5.查询附件信息
        List<SysFile> sysFiles = sysFileDao.queryByRelationId(declare.getFilePath());
        if(sysFiles.size()>0) {
            SysFile sysFile = sysFiles.get(0);
            declare.setSysFile(sysFile);
        }
    }
}
