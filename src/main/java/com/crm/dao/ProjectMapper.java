package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProjectBO;
import com.crm.entity.Project;
import com.github.abel533.mapper.Mapper;
public interface ProjectMapper extends Mapper<Project>{
	public List<DataStat> getProjectStatList();
	public List<Project> queryByCustomId(@Param("customId") String customId);
	public List<ProjectBO> getProjectList(Project param);
	public List<ProjectBO> getProjectListByIn(@Param("ids") List<String> ids);
	public Project queryByName(@Param("name") String name);
	public Integer getMaxProjectIndex();
	public ProjectBO getProjectById(@Param("id") String id);
	
	public List<DataStat> getProjectStatListByCreateTime(@Param("startTime") Long startTime,@Param("endTime") Long endTime);
	public Integer getProjectCount(@Param("startTime") Long startTime,@Param("endTime") Long endTime);
}
