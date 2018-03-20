package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProjectBO;
import com.crm.entity.Project;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectMapper extends Mapper<Project>{
	List<DataStat> getProjectStatList();
	List<Project> queryByCustomId(@Param("customId") String customId);
	List<ProjectBO> getProjectList(Project param);
	List<ProjectBO> getProjectListByIn(@Param("ids") List<String> ids);
	Project queryByName(@Param("name") String name);
	Integer getMaxProjectIndex();
	ProjectBO getProjectById(@Param("id") String id);

	List<DataStat> getProjectStatListByCreateTime(@Param("startTime") Long startTime,@Param("endTime") Long endTime);
	Integer getProjectCount(@Param("startTime") Long startTime,@Param("endTime") Long endTime);
}
