package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.LogBO;
import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.Log;
import tk.mybatis.mapper.common.Mapper;

public interface LogMapper extends Mapper<Log>{
	List<LogBO> getLogList(@Param("userId") String userId,@Param("count") int count);
	List<LogBO> getLogListNoParam();
	Integer queryCountByUserId(@Param("userId") String userId);

	List<UserBO> getUserListByCustomId(@Param("customId") String customId);
	List<UserBO> getUserListByProjectId(@Param("projectId") String projectId);

	Integer updateLogByUserIdAndIsRead(@Param("isRead") String isRead,@Param("userId") String userId);
	
}
