package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.ProjectFileBO;
import com.crm.entity.ProjectFile;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectFileMapper extends Mapper<ProjectFile>{
	List<ProjectFileBO> queryFileByProjectId(@Param("projectId") String projectId);
	ProjectFile queryByName(@Param("name") String name);
}
