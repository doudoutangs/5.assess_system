package com.assess.modules.sys.service.impl;

import com.assess.common.utils.PageUtils;
import com.assess.common.utils.Query;
import com.assess.modules.sys.dao.SysFileDao;
import com.assess.modules.sys.entity.SysDict;
import com.assess.modules.sys.entity.SysFile;
import com.assess.modules.sys.service.SysFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileDao, SysFile> implements SysFileService {


    @Override
    public List<SysFile> queryByRelationId(String relationId) {
        Map<String, Object> params = new HashMap<String, Object>();
        IPage<SysFile> page = this.page(
                new Query<SysFile>().getPage(params),
                new QueryWrapper<SysFile>()
                        .eq(StringUtils.isNotBlank(relationId), "relation_id", relationId)

        );
        return page.getRecords();
    }
}
