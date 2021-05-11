package com.assess.modules.assess.service.impl;

import com.assess.common.utils.Constant;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.assess.constant.DeclareTypeEnum;
import com.assess.modules.assess.dao.AssessApprovalDao;
import com.assess.modules.assess.dao.AssessDeclareDao;
import com.assess.modules.assess.dao.AssessStatisDao;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.entity.AssessDeclare;
import com.assess.modules.assess.entity.AssessSet;
import com.assess.modules.assess.entity.AssessStatis;
import com.assess.modules.assess.service.AssessDeclareService;
import com.assess.modules.assess.service.AssessItemService;
import com.assess.modules.assess.service.AssessSetService;
import com.assess.modules.assess.service.AssessStatisService;
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

import java.util.*;


@Service
public class AssessStatisServiceImpl extends ServiceImpl<AssessStatisDao, AssessStatis> implements AssessStatisService {
    @Autowired
    AssessStatisDao statisDao;
    @Autowired
    AssessSetService setService;
    @Autowired
    SysDeptService deptService;

    @Override
    public List statis() {
        List<String> setList = setList();
        Map<String, Object> params = new HashMap<>();
        List list = new ArrayList();
        list.add(setList);

        List<SysDept> deptList = deptService.queryByDetpId(1l);
        for (SysDept dept : deptList) {
            params.put("deptId", dept.getDeptId());
            List<AssessStatis> statis = statisDao.statis(params);
            if (statis.size() > 0) {
                List<Object> compose = compose(statis);
                list.addAll(compose);
            }else{
                //没有数据部门，默认都为0
                List<Object> dep =  new ArrayList<Object>();
                dep.add(dept.getName());
                for (int i = 0; i < setList.size(); i++) {
                    dep.add(0.00) ;
                }
                list.add(dep.toArray());
            }
        }

        return list;
    }

    private List<String> setList() {
        List<AssessSet> list = setService.list();
        List<String> hang = new ArrayList<String>();
        hang.add("product");
        for (AssessSet assessSet : list) {
            hang.add(assessSet.getTitle());
        }
        return hang;
    }

    private  List<Object> compose(List<AssessStatis> list) {
        List result = new ArrayList();
        List<Object> hang = new ArrayList<Object>();
        if (list.size() > 0) {
            SysDept dept = deptService.getById(list.get(0).getDeptId());
            hang.add(dept.getName());
            for (AssessStatis statis : list) {
                //没有数据的主体，默认数据为0
                Float approvalScoreAvg = statis.getApprovalScoreAvg();
                hang.add(approvalScoreAvg);
            }
            result.add(hang.toArray());
        }
        return result;
    }

    private boolean search(Long assessId) {
        List<AssessSet> setList = setService.list();
        List<Long> setIds = new ArrayList<Long>();
        for (AssessSet assessSet : setList) {
            if (assessId == assessSet.getId()) {
                return true;
            }
        }
        return false;
    }
}
