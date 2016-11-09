package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.DataStat;
import com.crm.dao.ProjectMapper;
import com.crm.entity.Project;

@Service("projectService")
public class ProjectService extends BaseService<Project>{
	@Autowired
	private ProjectMapper projectMapper;
	
	public List<DataStat> getProjectStatList(){
		return projectMapper.getProjectStatList();
	}
	
	
}
