package com.crm.dao;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.entity.SysMail;
import tk.mybatis.mapper.common.Mapper;

public interface SysMailMapper extends Mapper<SysMail>{
	SysMail queryByEmail(@Param("email") String email);
}
