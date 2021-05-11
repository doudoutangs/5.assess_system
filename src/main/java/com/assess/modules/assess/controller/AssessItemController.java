/**
 *
 */

package com.assess.modules.assess.controller;


import com.assess.common.annotation.SysLog;
import com.assess.common.utils.DateUtils;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.R;
import com.assess.common.validator.ValidatorUtils;
import com.assess.modules.assess.entity.AssessAndItem;
import com.assess.modules.assess.entity.AssessItem;
import com.assess.modules.assess.service.AssessAndItemService;
import com.assess.modules.assess.service.AssessItemService;
import com.assess.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/assess/item")
public class AssessItemController extends AbstractController {

    @Autowired
    private AssessItemService itemService;
    @Autowired
    private AssessAndItemService assessAndItemService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("assess:item:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = itemService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/items")
    public R users() {
        List<AssessItem> list = itemService.list();
        return R.ok().put("items", list);
    }

    /**
     * 详情
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("assess:item:info")
    @ResponseBody
    public R info(@PathVariable("id") Long id) {
        AssessItem item = itemService.getById(id);

        return R.ok().put("item", item);
    }

    /**
     * 保存
     */
    @SysLog("保存")
    @RequestMapping("/save")
    @RequiresPermissions("assess:item:save")
    public R save(@RequestBody AssessItem item) {
        ValidatorUtils.validateEntity(item);
        item.setCreateTime(DateUtils.currentTimeFormat());

        itemService.save(item);
        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改")
    @RequestMapping("/update")
    @RequiresPermissions("assess:item:update")
    public R update(@RequestBody AssessItem item) {
        ValidatorUtils.validateEntity(item);
        item.setUpdateTime(DateUtils.currentTimeFormat());
        itemService.update(item);
        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除")
    @RequestMapping("/delete")
    @RequiresPermissions("assess:item:delete")
    public R delete(@RequestBody Long[] ids) {
        for (Long id : ids) {
            List<AssessAndItem> list = assessAndItemService.queryByItemId(id);
            if (list.size() > 0) {
                return R.error("该考核项已被使用，无法删除");
            }
        }
        itemService.deleteBatch(ids);
        return R.ok();
    }

}