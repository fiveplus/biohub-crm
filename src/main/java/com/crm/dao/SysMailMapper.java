package com.crm.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.SysMail;
import com.github.abel533.mapper.Mapper;
public interface SysMailMapper extends Mapper<SysMail>{
	public SysMail queryByEmail(@Param("email") String email);
}
