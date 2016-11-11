package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.LogBO;
import com.crm.dao.LogMapper;
import com.crm.entity.Log;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
}
