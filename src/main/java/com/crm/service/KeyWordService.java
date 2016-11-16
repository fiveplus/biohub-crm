package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.KeyWordMapper;
import com.crm.entity.KeyWord;

@Service("keyWordService")
public class KeyWordService extends BaseService<KeyWord>{
	@Autowired
	private KeyWordMapper keyWordMapper;
	
	public KeyWord queryByName(String name){
		return keyWordMapper.queryByName(name);
	}
	
}
