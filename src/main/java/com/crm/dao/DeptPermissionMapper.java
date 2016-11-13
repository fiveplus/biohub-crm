package com.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.DeptPermission;
import com.github.abel533.mapper.Mapper;

public interface DeptPermissionMapper extends Mapper<DeptPermission>{
	public int deletePermissionByDeptId(@Param("deptId") String deptId);
}
