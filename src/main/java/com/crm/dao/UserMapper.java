package com.crm.dao;

import org.springframework.stereotype.Repository;

import com.crm.entity.User;
import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<User>{
	public User getUserByLoginNameAndPassword(String loginName,String password);
	public User getUserByLoginName(String loginName);
}
