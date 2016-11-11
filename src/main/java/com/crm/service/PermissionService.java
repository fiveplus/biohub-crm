package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.PermissionMapper;
import com.crm.entity.Permission;
import com.crm.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("permissionService")
public class PermissionService extends BaseService<Permission>{
	@Autowired
	private PermissionMapper permissionMapper;
	
	public List<Permission> getParentMenu(){
		return permissionMapper.getParentMenu();
	}
	
	public List<Permission> getChildPermission(String deptId){
		return permissionMapper.getChildPermission(deptId);
	}
	
	
	
}
