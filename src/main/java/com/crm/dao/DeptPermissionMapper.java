package com.crm.dao;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.entity.DeptPermission;
import tk.mybatis.mapper.common.Mapper;

public interface DeptPermissionMapper extends Mapper<DeptPermission> {
	int deletePermissionByDeptId(@Param( "deptId") String deptId);
}
