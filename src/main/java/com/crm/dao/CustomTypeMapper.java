package com.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.crm.entity.CustomType;
import com.github.abel533.mapper.Mapper;
public interface CustomTypeMapper extends Mapper<CustomType>{
	public CustomType queryByName(@Param("name") String name);
}
