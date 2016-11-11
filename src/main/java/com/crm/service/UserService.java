package com.crm.service;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.UserBO;
import com.crm.dao.UserMapper;
import com.crm.entity.User;
import com.crm.utils.PasswordHelper;
import com.crm.utils.PropertiesUtils;
import com.crm.utils.Resource;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service("userService")
public class UserService extends BaseService<User>{
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
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
	
	public Integer saveUser(User us){
		us.setLocked(0);
		us.setStatus(Resource.Y);
		us.setInformation("");
		us.setModifyTime(StringUtils.getDateToLong(new Date()));
		us.setLoginTime(0l);
		us.setPicture("");
		
		PropertiesUtils util = new PropertiesUtils();
		us.setPassword(util.getProperty("init.pass"));
		us = passwordHelper.encryptPassword(us);
		Integer count = saveSelect(us);
		return count;
	}
	
	
}
