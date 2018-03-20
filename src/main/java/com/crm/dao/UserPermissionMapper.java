package com.crm.dao;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;
import com.crm.entity.UserPermission;
import tk.mybatis.mapper.common.Mapper;

public interface UserPermissionMapper extends Mapper<UserPermission> {
	int deletePermissionByUserId(@Param("userId") String userId);
}
