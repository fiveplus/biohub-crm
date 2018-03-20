package com.crm.dao;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.entity.ProjectType;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectTypeMapper extends Mapper<ProjectType>{
	ProjectType queryByName(@Param("name") String name);
}
