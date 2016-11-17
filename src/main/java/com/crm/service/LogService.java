package com.crm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.LogBO;
import com.crm.controller.admin.bo.UserBO;
import com.crm.dao.LogMapper;
import com.crm.entity.Log;
import com.crm.entity.User;
import com.crm.utils.LogUtil;
import com.crm.utils.StringUtils;
import com.crm.utils.LogUtil.LogObject;
import com.crm.utils.LogUtil.LogType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.log.LogUtils;

@Service("logService")
public class LogService extends BaseService<Log>{
	@Autowired
	private LogMapper logMapper;
	
	public List<LogBO> getLogList(String userId,int count){
		return logMapper.getLogList(userId, count);
	}
	
	public PageInfo<LogBO> getLogListNoParam(int page){
		PageHelper.startPage(page,10);
		List<LogBO> list = logMapper.getLogListNoParam();
		PageInfo<LogBO> p = new PageInfo<>(list);
		return p;
	}
	
	public <T> Integer saveLog(User user,Date d,T t,LogType logType,LogObject logObj,String id){
		LogUtil<T> util = new LogUtil<T>();
		String info = util.getLog(user, t, d, logType);
		Log log = new Log();
		log.setCreateTime(StringUtils.getDateToLong(d));
		log.setInformation(info);
		log.setUserId(user.getId());
		if(logObj == LogObject.Custom){
			log.setCustomId(id);
		}
		if(logObj == LogObject.Project){
			log.setProjectId(id);
		}
		return logMapper.insertSelective(log);
	}
	
	public Integer queryCountByUserId(String userId){
		return logMapper.queryCountByUserId(userId);
	}
	
	public List<UserBO> getUserListByCustomId(String customId){
		return logMapper.getUserListByCustomId(customId);
	}
	
	public List<UserBO> getUserListByProjectId(String projectId){
		return logMapper.getUserListByProjectId(projectId);
	}
	
	
	
}
