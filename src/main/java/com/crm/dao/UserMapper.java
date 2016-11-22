package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.User;
import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<User>{
	public User getUserByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
	public User getUserByLoginName(@Param("loginName") String loginName);
	public User getUserByUserName(@Param("userName") String userName);
	public List<UserBO> getUserList();
	public UserBO getUserById(@Param("id") String id);
	public List<User> queryUserListByDeptId(@Param("deptId") String deptId);
}
