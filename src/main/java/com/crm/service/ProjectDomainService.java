package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.ProjectDomainBO;
import com.crm.dao.ProjectDomainMapper;
import com.crm.entity.ProjectDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("projectDomainService")
public class ProjectDomainService extends BaseService<ProjectDomain>{
	@Autowired
	private ProjectDomainMapper projectDomainMapper;
	
	public ProjectDomain queryByName(String name){
		return projectDomainMapper.queryByName(name);
	}
	
	public PageInfo<ProjectDomainBO> queryList(int page){
		PageHelper.startPage(page, 10);
		List<ProjectDomainBO> list = projectDomainMapper.queryList();
		PageInfo<ProjectDomainBO> p = new PageInfo<>(list);
		return p;
	}
	
	public List<ProjectDomain> getParentList(){
		return projectDomainMapper.getParentList();
	}
	
}
