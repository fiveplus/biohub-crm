package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.SysMailMapper;
import com.crm.entity.SysMail;

@Service("sysMailService")
public class SysMailService extends BaseService<SysMail>{
	
	@Autowired
	private SysMailMapper sysMailMapper;
	
	public SysMail queryByEmail(String email){
		return sysMailMapper.queryByEmail(email);
	}
}
