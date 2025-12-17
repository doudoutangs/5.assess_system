/**
 *
 */

package com.assess.modules.assess.controller;


import com.assess.common.annotation.SysLog;
import com.assess.common.utils.DateUtils;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.R;
import com.assess.common.validator.ValidatorUtils;
import com.assess.modules.assess.constant.ApprovalStatusEnum;
import com.assess.modules.assess.constant.DeclareStatusEnum;
import com.assess.modules.assess.entity.AssessApproval;
import com.assess.modules.assess.entity.AssessDeclare;
import com.assess.modules.assess.service.AssessApprovalService;
import com.assess.modules.assess.service.AssessDeclareService;
import com.assess.modules.sys.controller.AbstractController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 15:12
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 
 */
@RestController
@RequestMapping("/assess/approval")
public class AssessApprovalController extends AbstractController {
    @Autowired
    private AssessApprovalService approvalService;
    @Autowired
    private AssessDeclareService declareService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("assess:approval:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = approvalService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("assess:approval:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        AssessApproval approval = approvalService.getById(id);

        return R.ok().put("approval", approval);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @RequestMapping("/save")
    @RequiresPermissions("assess:approval:save")
    public R save(@RequestBody AssessApproval approval) {
        ValidatorUtils.validateEntity(approval);
        approval.setCreateTime(DateUtils.currentTimeFormat());
        approval.setUserId(getUserId());
        Long declareId = approval.getDeclareId();
        AssessDeclare declare = declareService.getById(declareId);
        if (DeclareStatusEnum.WAIT.getCode() == declare.getStatus()) {
            return R.error("未申报记录不可进行审批");
        }
        //不允许自己审批自己的申报记录
        if (declare.getUserId() == getUserId()) {
            return R.error("不允许自己审批自己的申报记录");
        }
        List<AssessApproval> list =  approvalService.list(new QueryWrapper<AssessApproval>()
                .eq("declare_id",declareId));
        if (list.size()>0) {
            return R.error("已审批，请勿重复审批");
        }
        //1.保存审批记录
        approvalService.save(approval);
        //2.更新申报记录信息
        Integer score = approval.getScore();
        Integer status = approval.getStatus();
        Integer finalState = DeclareStatusEnum.NO_PASS.getCode();
        if (ApprovalStatusEnum.PASS.getCode() == status) {
            finalState = DeclareStatusEnum.PASS.getCode();
        }
        AssessDeclare d = new AssessDeclare();
        d.setId(declareId);
        d.setApprovalScore(score);
        d.setStatus(finalState);
        d.setUpdateTime(DateUtils.currentTimeFormat());
        declareService.update(d);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @RequestMapping("/update")
    @RequiresPermissions("assess:approval:update")
    public R update(@RequestBody AssessApproval approval) {
        ValidatorUtils.validateEntity(approval);
        approval.setUpdateTime(DateUtils.currentTimeFormat());
        AssessDeclare declare = declareService.getById(approval.getDeclareId());
        if (DeclareStatusEnum.WAIT.getCode() == declare.getStatus()) {
            return R.error("未申报记录不可进行审批");
        }
        //1.更新审批信息
        approvalService.saveOrUpdate(approval);
        //2.更新申报记录信息
        Long declareId = approval.getDeclareId();
        Integer score = approval.getScore();
        Integer status = approval.getStatus();
        Integer finalState = DeclareStatusEnum.NO_PASS.getCode();
        if (ApprovalStatusEnum.PASS.getCode() == status) {
            finalState = DeclareStatusEnum.PASS.getCode();
        }
        AssessDeclare d = new AssessDeclare();
        d.setId(declareId);
        d.setApprovalScore(score);
        d.setStatus(finalState);
        d.setUpdateTime(DateUtils.currentTimeFormat());
        declareService.update(d);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @RequestMapping("/delete")
    @RequiresPermissions("assess:approval:delete")
    public R delete(@RequestBody Long[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            AssessApproval approval = approvalService.getByDeclareId(id);
            if (null == approval) {
                return R.error("第" + (i + 1) + "条信息，无审批记录，不可删除");
            }
            AssessDeclare d = new AssessDeclare();
            d.setId(approval.getDeclareId());
            d.setApprovalScore(0);
            d.setStatus(DeclareStatusEnum.NO_APPROVAL.getCode());
            d.setUpdateTime(DateUtils.currentTimeFormat());
            //1.申报状态修改
            declareService.update(d);
            //2.删除审批记录
            approvalService.removeById(approval.getId());
        }
        return R.ok();
    }

}