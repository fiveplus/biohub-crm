package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.CustomLocation;
import com.github.abel533.mapper.Mapper;
@ResponseBody
public interface CustomLocationMapper extends Mapper<CustomLocation>{
	public CustomLocation queryByName(@Param("name") String name);
}
