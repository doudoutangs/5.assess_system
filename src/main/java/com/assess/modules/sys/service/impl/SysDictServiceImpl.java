package com.assess.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.sys.dao.SysDictDao;
import com.assess.modules.sys.entity.SysDict;
import com.assess.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 15:13
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 3. gitee(码云)主页：https://gitee.com/spdoudoutang
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDict> implements SysDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");

        IPage<SysDict> page = this.page(
                new Query<SysDict>().getPage(params),
                new QueryWrapper<SysDict>()
                        .like(StringUtils.isNotBlank(name), "name", name)
//                        .orderByAsc("order_num")
                        .orderByDesc("create_time")

        );

        return new PageUtils(page);
    }

    @Override
    public List<SysDict> getListByType(String type) {
        List<SysDict> list = this.list(new QueryWrapper<SysDict>()
                .eq(StringUtils.isNotBlank(type), "type", type)
                .orderByAsc("order_num"));

        return list;
    }

    @Override
    public String getNameByType(String code, List<SysDict> list) {
        for (SysDict d : list) {
            if(code.equals(d.getCode())){
                return d.getValue();
            }
        }
        return null;
    }

}
