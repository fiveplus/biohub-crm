package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.ProjectType;
import com.github.abel533.mapper.Mapper;

public interface ProjectTypeMapper extends Mapper<ProjectType>{
	public ProjectType queryByName(@Param("name") String name);
}