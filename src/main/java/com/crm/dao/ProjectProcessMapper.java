package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProcessBO;
import com.crm.entity.ProjectProcess;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectProcessMapper extends Mapper<ProjectProcess>{
	List<ProcessBO> queryProcessByProjectId(@Param("projectId") String projectId);
	List<DataStat> getProcessStatListByCreateTime(@Param("startTime") Long startTime,@Param("endTime") Long endTime);
	List<ProcessBO> getProcessListByCreateTime(@Param("userId") String userId,@Param("startTime") Long startTime,@Param("endTime") Long endTime);
}
