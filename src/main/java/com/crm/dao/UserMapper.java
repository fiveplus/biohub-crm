package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User>{
	User getUserByLoginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);
	User getUserByLoginName(@Param("loginName") String loginName);
	User getUserByUserName(@Param("userName") String userName);
	List<UserBO> getUserList();
	UserBO getUserById(@Param("id") String id);
	List<User> queryUserListByDeptId(@Param("deptId") String deptId);
}
