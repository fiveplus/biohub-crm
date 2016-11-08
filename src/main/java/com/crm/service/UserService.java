package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.UserMapper;
import com.crm.entity.User;


@Service
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginNameAndPassword(String loginName,String password){
		return userMapper.getUserByLoginNameAndPassword(loginName,password);
	}
}
