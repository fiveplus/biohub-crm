package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.ProjectFileBO;
import com.crm.entity.ProjectFile;
import com.github.abel533.mapper.Mapper;

public interface ProjectFileMapper extends Mapper<ProjectFile>{
	public List<ProjectFileBO> queryFileByProjectId(@Param("projectId") String projectId);
	public ProjectFile queryByName(@Param("name") String name);
}
