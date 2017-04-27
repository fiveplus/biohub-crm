package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.ProjectTypeMapper;
import com.crm.entity.ProjectType;

@Service("projectTypeService")
public class ProjectTypeService extends BaseService<ProjectType>{
	@Autowired
	private ProjectTypeMapper projectTypeMapper;
	
	public ProjectType queryByName(String name){
		return projectTypeMapper.queryByName(name);
	}
}
