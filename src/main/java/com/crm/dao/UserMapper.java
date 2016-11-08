package com.crm.dao;

import com.crm.entity.User;
import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<User>{
	public User getUserByLoginNameAndPassword(String loginName,String password);
}
