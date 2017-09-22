package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.LogBO;
import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.Log;
import com.github.abel533.mapper.Mapper;
public interface LogMapper extends Mapper<Log>{
	public List<LogBO> getLogList(@Param("userId") String userId,@Param("count") int count);
	public List<LogBO> getLogListNoParam();
	public Integer queryCountByUserId(@Param("userId") String userId);
	
	public List<UserBO> getUserListByCustomId(@Param("customId") String customId);
	public List<UserBO> getUserListByProjectId(@Param("projectId") String projectId);
	
	public Integer updateLogByUserIdAndIsRead(@Param("isRead") String isRead,@Param("userId") String userId);
	
}
