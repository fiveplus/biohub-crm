package com.crm.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProjectBO;
import com.crm.dao.ProjectDomainMapper;
import com.crm.dao.ProjectMapper;
import com.crm.entity.Project;
import com.crm.entity.ProjectDomain;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("projectService")
public class ProjectService extends BaseService<Project>{
	@Autowired
	private ProjectMapper projectMapper;
	
	@Autowired
	private ProjectDomainService projectDomainService;
	
	public List<DataStat> getProjectStatList(){
		return projectMapper.getProjectStatList();
	}
	
	public List<Project> queryByCustomId(String customId){
		return projectMapper.queryByCustomId(customId);
	}
	
	public PageInfo<ProjectBO> getProjectList(int page,Project param){
		PageHelper.startPage(page, 10);
		List<ProjectBO> list = projectMapper.getProjectList(param);
		PageInfo<ProjectBO> p = new PageInfo<>(list);
		return p;
	}
	
	public List<ProjectBO> getProjectList(Project param){
		return projectMapper.getProjectList(param);
	}
	
	public ProjectBO getProjectById(String id){
		return projectMapper.getProjectById(id);
	}
	
	
	public List<ProjectBO> getProjectListByIn(List<String> ids){
		return projectMapper.getProjectListByIn(ids);
	}
	
	public Project queryByName(String name){
		return projectMapper.queryByName(name);
	}
	
	public Integer saveProject(Project p){
		int max = projectMapper.getMaxProjectIndex();
		p.setProjectIndex(max+1);
		ProjectDomain pd = projectDomainService.queryById(p.getDomainId());
		int temp = (max+1) % 1000;
		String random = "";
		if(temp <= 9){
			random = "00" + temp;
		}
		if(temp > 9 && temp <= 99){
			random = "0" + temp;
		}
		if(temp > 99){
			random = "" + temp;
		}
		String num = StringUtils.getDateToSmallString(new Date())+"-"+pd.getEnglishShort()+"-"+random;
		p.setProjectNum(num);
		return saveSelect(p);
	}
	
	public List<DataStat> getProjectStatListByCreateTime(Long startTime,Long endTime){
		return projectMapper.getProjectStatListByCreateTime(startTime, endTime);
	}
	
	public Integer getProjectCount(Long startTime,Long endTime){
		return projectMapper.getProjectCount(startTime, endTime);
	}
	
	
}
