package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.CustomTypeMapper;
import com.crm.entity.Custom;
import com.crm.entity.CustomType;

@Service("customTypeService")
public class CustomTypeService extends BaseService<CustomType>{
	@Autowired
	private CustomTypeMapper customTypeMapper;
	
	public CustomType queryByName(String name){
		return customTypeMapper.queryByName(name);
	}
	
}
