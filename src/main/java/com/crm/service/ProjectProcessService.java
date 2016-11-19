package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProcessBO;
import com.crm.dao.ProjectProcessMapper;
import com.crm.entity.ProjectProcess;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("processService")
public class ProjectProcessService extends BaseService<ProjectProcess>{
	@Autowired
	private ProjectProcessMapper processMapper;
	
	public PageInfo<ProcessBO> queryProcessByProjectId(String projectId,int page){
		PageHelper.startPage(page, 10);
		List<ProcessBO> list = processMapper.queryProcessByProjectId(projectId);
		PageInfo<ProcessBO> p = new PageInfo<>(list);
		return p;
	}
	
	public List<ProcessBO> queryProcessByProjectId(String projectId){
		return processMapper.queryProcessByProjectId(projectId);
	}
	
	public List<DataStat> getProcessStatListByCreateTime(Long startTime,Long endTime){
		return processMapper.getProcessStatListByCreateTime(startTime, endTime);
	}

	public List<ProcessBO> getProcessListByCreateTime(String userId,Long startTime,Long endTime){
		return processMapper.getProcessListByCreateTime(userId, startTime, endTime);
	}
	
	public PageInfo<ProcessBO> getProcessListByCreateTime(int page,String userId,Long startTime,Long endTime){
		PageHelper.startPage(page, 10);
		List<ProcessBO> list = processMapper.getProcessListByCreateTime(userId, startTime, endTime);
		PageInfo<ProcessBO> p = new PageInfo<>(list);
		return p;
	}
	
	
	
}
