package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.KeyWord;
import com.github.abel533.mapper.Mapper;

@ResponseBody
public interface KeyWordMapper extends Mapper<KeyWord>{
	public KeyWord queryByName(@Param("name") String name);
}
