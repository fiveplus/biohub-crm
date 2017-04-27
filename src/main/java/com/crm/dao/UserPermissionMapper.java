package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import com.crm.entity.UserPermission;
import com.github.abel533.mapper.Mapper;
public interface UserPermissionMapper extends Mapper<UserPermission>{
	public int deletePermissionByUserId(@Param("userId") String userId);
}
