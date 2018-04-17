package com.crm.dao;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.crm.entity.CustomLocation;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

public interface CustomLocationMapper extends Mapper<CustomLocation>{
	CustomLocation queryByName(@Param("name") String name);
}
