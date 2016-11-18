package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.ProcessBO;
import com.crm.entity.ProjectProcess;
import com.github.abel533.mapper.Mapper;

public interface ProjectProcessMapper extends Mapper<ProjectProcess>{
	public List<ProcessBO> queryProcessByProjectId(@Param("projectId") String projectId);
}
