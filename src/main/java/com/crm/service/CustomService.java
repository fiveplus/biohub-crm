package com.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.DataStat;
import com.crm.dao.CustomMapper;
import com.crm.entity.Custom;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("customService")
public class CustomService extends BaseService<Custom>{
	
	@Autowired
	private CustomMapper customMapper;
	
	public List<DataStat> getCustomStatList(){
		return customMapper.getCustomStatList();
	}
	
	public PageInfo<CustomBO> getCustomList(int page,Custom param){
		PageHelper.startPage(page, 10);
		List<CustomBO> list = customMapper.getCustomList(param);
		PageInfo<CustomBO> p = new PageInfo<>(list);
		return p;
	}
	
	public List<CustomBO> getCustomList(Custom param){
		List<CustomBO> list = customMapper.getCustomList(param);
		return list;
	}
	
	public List<CustomBO> getCustomListByIn(String ids){
		return customMapper.getCustomListByIn(ids);
	}
	
	public Custom queryByEmail(String email){
		return customMapper.queryByEmail(email);
	}
	
	public Integer queryCount(){
		return customMapper.queryCount();
	}
	
	
}
