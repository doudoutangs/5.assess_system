package com.assess.modules.sys.dao;

import com.assess.modules.sys.entity.SysFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据字典
 *
 * @author 
 */
@Mapper
public interface SysFileDao extends BaseMapper<SysFile> {
	List<SysFile> queryByRelationId(String relationId);
}
