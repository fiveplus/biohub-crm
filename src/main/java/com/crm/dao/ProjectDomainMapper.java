package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.ProjectDomainBO;
import com.crm.entity.ProjectDomain;
import com.github.abel533.mapper.Mapper;

public interface ProjectDomainMapper extends Mapper<ProjectDomain>{
	public ProjectDomain queryByName(@Param("name") String name);
	public List<ProjectDomainBO> queryList();
	public List<ProjectDomain> getParentList();
	public List<ProjectDomain> getChildList(@Param("pid") String pid);
}
