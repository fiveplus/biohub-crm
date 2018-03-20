package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.ProjectDomainBO;
import com.crm.entity.ProjectDomain;
import tk.mybatis.mapper.common.Mapper;

public interface ProjectDomainMapper extends Mapper<ProjectDomain>{
	ProjectDomain queryByName(@Param("name") String name);
	List<ProjectDomainBO> queryList();
	List<ProjectDomain> getParentList();
	List<ProjectDomain> getChildList(@Param("pid") String pid);
}
