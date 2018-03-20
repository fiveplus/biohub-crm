package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Permission;
import tk.mybatis.mapper.common.Mapper;

public interface PermissionMapper extends Mapper<Permission>{
	List<Permission> getParentMenu();
	List<Permission> getChildPermissionByDeptId(@Param("deptId") String deptId);
	List<Permission> getChildPermissionByUserId(@Param("userId") String userId);
	List<Permission> getPermissionByParentId(@Param("parentId") String parentId);
	int getCountByParentId(@Param("parentId") String parentId);
	List<Permission> getParentPermission();
}
