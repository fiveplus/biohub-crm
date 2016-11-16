package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.CustomLocationMapper;
import com.crm.entity.CustomLocation;

@Service("customLocationService")
public class CustomLocationService extends BaseService<CustomLocation>{
	@Autowired
	private CustomLocationMapper customLocationMapper;
	
	public CustomLocation queryByName(String name){
		return customLocationMapper.queryByName(name);
	}
	
}
