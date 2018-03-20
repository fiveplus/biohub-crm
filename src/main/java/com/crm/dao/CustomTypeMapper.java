package com.crm.dao;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.entity.CustomType;
import tk.mybatis.mapper.common.Mapper;

public interface CustomTypeMapper extends Mapper<CustomType>{
	CustomType queryByName(@Param("name") String name);
}
