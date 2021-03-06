package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.PermissionMapper;
import com.crm.entity.Permission;

@Service("permissionService")
public class PermissionService extends BaseService<Permission>{
	@Autowired
	private PermissionMapper permissionMapper;
	
	public List<Permission> getParentMenu(){
		return permissionMapper.getParentMenu();
	}
	
	public List<Permission> getChildPermissionByDeptId(String deptId){
		return permissionMapper.getChildPermissionByDeptId(deptId);
	}
	
	public List<Permission> getChildPermissionByUserId(String userId){
		return permissionMapper.getChildPermissionByUserId(userId);
	}
	
	public List<Permission> getPermissionByParentId(String parentId){
		return permissionMapper.getPermissionByParentId(parentId);
	}
	
	public int getCountByParentId(String parentId){
		return permissionMapper.getCountByParentId(parentId);
	}
	
	public List<Permission> getParentPermission(){
		return permissionMapper.getParentPermission();
	}
	
}
