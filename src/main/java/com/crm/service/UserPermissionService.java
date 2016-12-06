package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.UserPermissionMapper;
import com.crm.entity.UserPermission;

@Service("userPermissionService")
public class UserPermissionService extends BaseService<UserPermission>{
	
	@Autowired
	private UserPermissionMapper userPermissionMapper;
	
	public int deletePermissionByUserId(String userId){
		return userPermissionMapper.deletePermissionByUserId(userId);
	}
	
}
