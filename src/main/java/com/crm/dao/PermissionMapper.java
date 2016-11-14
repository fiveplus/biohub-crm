package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Permission;
import com.github.abel533.mapper.Mapper;
@ResponseBody
public interface PermissionMapper extends Mapper<Permission>{
	public List<Permission> getParentMenu();
	public List<Permission> getChildPermission(@Param("deptId") String deptId);
	public List<Permission> getPermissionByParentId(@Param("parentId") String parentId);
	public int getCountByParentId(@Param("parentId") String parentId);
	public List<Permission> getParentPermission();
}
