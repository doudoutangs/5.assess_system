package com.assess.modules.assess.controller;


import com.assess.common.annotation.SysLog;
import com.assess.common.utils.DateUtils;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.R;
import com.assess.common.validator.ValidatorUtils;
import com.assess.modules.assess.constant.DeclareStatusEnum;
import com.assess.modules.assess.constant.DeclareTypeEnum;
import com.assess.modules.assess.entity.AssessDeclare;
import com.assess.modules.assess.entity.AssessStaff;
import com.assess.modules.assess.service.AssessDeclareService;
import com.assess.modules.assess.service.AssessStaffService;
import com.assess.modules.sys.controller.AbstractController;
import com.assess.modules.sys.entity.SysUser;
import com.assess.modules.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 15:12
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 3. gitee(码云)主页：https://gitee.com/spdoudoutang
 */
@RestController
@RequestMapping("/assess/staff")
public class AssessStaffController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AssessStaffService staffService;
    @Autowired
    private AssessDeclareService declareService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("assess:staff:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = staffService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("assess:staff:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        AssessStaff staff = staffService.getById(id);

        return R.ok().put("staff", staff);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @RequestMapping("/save")
    @RequiresPermissions("assess:staff:save")
    public R save(@RequestBody AssessStaff staff) {
        ValidatorUtils.validateEntity(staff);
        staff.setCreateTime(DateUtils.currentTimeFormat());
        Long deptId = staff.getDeptId();
        List<SysUser> userIdList = new ArrayList<SysUser>();
        if (deptId == 1) {
            //添加所有部门
            userIdList = sysUserService.queryByNoDeptId(deptId);
        } else {
            userIdList = sysUserService.queryByDeptId(deptId);
        }
        Long assessId = staff.getAssessId();
        for (SysUser user : userIdList) {
            Long userId = user.getUserId();
            //检查一个考核下是否有重复人员
            List<AssessStaff> list = staffService.getByAssessId(assessId, userId);
            int count = list.size();
            if (count > 0) {
                AssessStaff s = list.get(0);
                String type = s.getType();
                if (type.equals("1")) {
                    return R.error(s.getUserName() + ",已纳入本次考核对象,请重新选择考核对象");
                }

            }
            staff.setUserId(userId);
            staffService.save(staff);
            //2.个人绩效申报增加记录
            AssessDeclare declare = new AssessDeclare();
            declare.setCreateTime(DateUtils.currentTimeFormat());
            declare.setAssessId(assessId);
            declare.setUserId(userId);
            declare.setApprovalScore(0);
            declare.setScore(0);
            String type = staff.getType();
            if (type.equals("1")) {
                declare.setStatus(DeclareStatusEnum.WAIT.getCode());
            }
            declareService.save(declare);
        }
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @RequestMapping("/update")
    @RequiresPermissions("assess:staff:update")
    public R update(@RequestBody AssessStaff staff) {
        ValidatorUtils.validateEntity(staff);
        staff.setUpdateTime(DateUtils.currentTimeFormat());
        Long assessId = staff.getAssessId();
        Long userId = staff.getUserId();
        staffService.update(staff);
        //2.个人绩效申报增加记录
        AssessDeclare declare = new AssessDeclare();
        declare.setCreateTime(DateUtils.currentTimeFormat());
        declare.setAssessId(assessId);
        declare.setUserId(userId);
        String type = staff.getType();
        if (type.equals("1")) {
            declare.setStatus(DeclareStatusEnum.WAIT.getCode());
        }

        declareService.save(declare);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @RequestMapping("/delete")
    @RequiresPermissions("assess:staff:delete")
    public R delete(@RequestBody Long[] ids) {
        Long[] idList = new Long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            AssessStaff staff = staffService.getById(id);
            Long assessId = staff.getAssessId();
            Long userId = staff.getUserId();
            List<AssessDeclare> list = declareService.list(new QueryWrapper<AssessDeclare>()
                    .eq("type", DeclareTypeEnum.WHOLE.getCode())
                    .eq("assess_id", assessId)
                    .eq("user_id", userId));
            AssessDeclare declare = list.get(0);
            if (declare.getStatus() != DeclareStatusEnum.WAIT.getCode()) {
                return R.error("该员工已进行该考核的申报，请先删除考核申报信息后在进行删除");
            }
            idList[i] = declare.getId();
        }
        //1.删除考核员工关系
        staffService.deleteBatch(ids);
        //2.删除考核申报
        declareService.deleteBatch(idList);

        return R.ok();
    }

}