package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.CustomType;
import com.github.abel533.mapper.Mapper;
@ResponseBody
public interface CustomTypeMapper extends Mapper<CustomType>{
	public CustomType queryByName(@Param("name") String name);
}
