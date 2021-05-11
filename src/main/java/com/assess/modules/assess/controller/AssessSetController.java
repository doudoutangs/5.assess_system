/**
 *
 */

package com.assess.modules.assess.controller;


import com.assess.common.annotation.SysLog;
import com.assess.common.utils.DateUtils;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.R;
import com.assess.common.validator.ValidatorUtils;
import com.assess.modules.assess.dao.AssessDeclareDao;
import com.assess.modules.assess.entity.AssessAndItem;
import com.assess.modules.assess.entity.AssessDeclare;
import com.assess.modules.assess.entity.AssessSet;
import com.assess.modules.assess.service.AssessAndItemService;
import com.assess.modules.assess.service.AssessItemService;
import com.assess.modules.assess.service.AssessSetService;
import com.assess.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/assess/set")
public class AssessSetController extends AbstractController {
    @Autowired
    private AssessSetService setService;

    @Autowired
    AssessAndItemService assessAndItemService;

    @Autowired
    AssessItemService assessItemService;
    @Autowired
    AssessDeclareDao declareDao;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("assess:set:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = setService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/assess")
    public R users() {
        List<AssessSet> list = setService.list();
        return R.ok().put("assess", list);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("assess:set:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        AssessSet set = setService.getById(id);
        //查询该考核的所有考核项
        List<AssessAndItem> relationList = assessAndItemService.queryByAssessId(id);
        List<Long> list = new ArrayList<Long>();
        for (AssessAndItem a : relationList) {
            list.add(a.getItemId());
        }
        set.setItemIdList(list);

        return R.ok().put("set", set);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @RequestMapping("/save")
    @RequiresPermissions("assess:set:save")
    public R save(@RequestBody AssessSet set) {
        ValidatorUtils.validateEntity(set);
        set.setCreateTime(DateUtils.currentTimeFormat());
        //1.保存考核
        setService.save(set);
        //2.保存考核项与考核关系
        List<Long> items = set.getItemIdList();
        List<AssessAndItem> list = new ArrayList<AssessAndItem>();
        Long assessId = set.getId();
        for (Long id : items) {
            AssessAndItem item = new AssessAndItem();
            item.setAssessId(assessId);
            item.setItemId(id);
            item.setCreateTime(DateUtils.currentTimeFormat());
            list.add(item);
        }
        assessAndItemService.saveBatch(list);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @RequestMapping("/update")
    @RequiresPermissions("assess:set:update")
    public R update(@RequestBody AssessSet set) {
        ValidatorUtils.validateEntity(set);
        set.setUpdateTime(DateUtils.currentTimeFormat());
        setService.update(set);
        //2.保存考核项与考核关系
        List<Long> items = set.getItemIdList();
        List<AssessAndItem> list = new ArrayList<AssessAndItem>();
        Long assessId = set.getId();
        for (Long id : items) {
            AssessAndItem item = new AssessAndItem();
            item.setAssessId(assessId);
            item.setItemId(id);
            item.setCreateTime(DateUtils.currentTimeFormat());
            list.add(item);
        }
        assessAndItemService.saveBatch(list);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @RequestMapping("/delete")
    @RequiresPermissions("assess:set:delete")
    public R delete(@RequestBody Long[] ids) {

        for (Long id : ids) {

            AssessDeclare d = new AssessDeclare();
            d.setAssessId(id);
            List<AssessDeclare> records = declareDao.getUserRecore(d);
            //1.判断该考核是否被使用
            if (records.size() > 0) {
                return R.error("该考核已被使用，无法删除");
            }
            List<Long> list = new ArrayList<Long>();
            List<AssessAndItem> relationList = assessAndItemService.queryByAssessId(id);
            for (AssessAndItem a : relationList) {
                list.add(a.getId());
            }
            //2.删除考核与考核项关联记录
            assessAndItemService.removeByIds(list);
        }
        //3.删除考核记录
        setService.deleteBatch(ids);
        return R.ok();
    }

}