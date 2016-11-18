package com.crm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.ProjectFileBO;
import com.crm.dao.ProjectFileMapper;
import com.crm.entity.ProjectFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("projectFileService")
public class ProjectFileService extends BaseService<ProjectFile>{
	
	@Autowired
	private ProjectFileMapper projectFileMapper;
	
	public List<ProjectFileBO> queryFileByProjectId(String projectId){
		return projectFileMapper.queryFileByProjectId(projectId);
	}
	
	public PageInfo<ProjectFileBO> queryFileByProjectId(String projectId,int page){
		PageHelper.startPage(page, 10);
		List<ProjectFileBO> list = projectFileMapper.queryFileByProjectId(projectId);
		PageInfo<ProjectFileBO> p = new PageInfo<>(list);
		return p;
	}
	
	public ProjectFile queryByName(String name){
		return projectFileMapper.queryByName(name);
	}
	
}
