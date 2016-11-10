package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.LogBO;
import com.crm.entity.Log;
import com.github.abel533.mapper.Mapper;

public interface LogMapper extends Mapper<Log>{
	public List<LogBO> getLogList(@Param("userId") String userId,@Param("count") int count);
}
