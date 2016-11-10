package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.UserBO;
import com.crm.dao.UserMapper;
import com.crm.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service("userService")
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginNameAndPassword(String loginName,String password){
		return userMapper.getUserByLoginNameAndPassword(loginName,password);
	}
	
	public User getUserByLoginName(String loginName){
		return userMapper.getUserByLoginName(loginName);
	}
	
	public UserBO getUserById(String id){
		return userMapper.getUserById(id);
	}
	
	public PageInfo<UserBO> getUserList(int page){
		PageHelper.startPage(page, 10);
		List<UserBO> list = userMapper.getUserList();
		PageInfo<UserBO> p = new PageInfo<UserBO>(list);
		return p;
	}
	
	
}
