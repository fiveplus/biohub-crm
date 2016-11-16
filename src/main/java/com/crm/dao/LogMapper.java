package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.LogBO;
import com.crm.entity.Log;
import com.github.abel533.mapper.Mapper;
@ResponseBody
public interface LogMapper extends Mapper<Log>{
	public List<LogBO> getLogList(@Param("userId") String userId,@Param("count") int count);
	public List<LogBO> getLogListNoParam();
	public Integer queryCountByUserId(@Param("userId") String userId);
}
