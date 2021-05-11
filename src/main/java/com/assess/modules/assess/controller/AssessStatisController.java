package com.assess.modules.assess.controller;


import com.assess.common.utils.R;
import com.assess.modules.assess.entity.AssessStatis;
import com.assess.modules.assess.service.AssessStatisService;
import com.assess.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/assess/statis")
public class AssessStatisController extends AbstractController {
    @Autowired
    private AssessStatisService statisService;

    /**
     * 所有列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("assess:statis:list")
    public R list(@RequestParam Map<String, Object> params) {
        List statis = statisService.statis();
        return R.ok().put("statis", statis);
    }



}