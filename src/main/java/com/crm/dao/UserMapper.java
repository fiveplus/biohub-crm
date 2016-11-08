package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crm.entity.User;
import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<User>{
	public User getUserByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
	public User getUserByLoginName(@Param("loginName") String loginName);
}
