package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.Permission;
import com.github.abel533.mapper.Mapper;

public interface PermissionMapper extends Mapper<Permission>{
	public List<Permission> getParentMenu();
	public List<Permission> getChildPermission(@Param("deptId") String deptId);
}
