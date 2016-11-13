package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.DeptPermissionMapper;
import com.crm.entity.DeptPermission;

@Service("deptPermissionService")
public class DeptPermissionService extends BaseService<DeptPermission>{
	
	@Autowired
	private DeptPermissionMapper deptPermissionMapper;
	
	public int deletePermissionByDeptId(String deptId){
		return deptPermissionMapper.deletePermissionByDeptId(deptId);
	}
	
	
	
}
