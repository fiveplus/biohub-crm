package com.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.dao.DepartmentMapper;
import com.crm.entity.Department;

@Service("departmentService")
public class DepartmentService extends BaseService<Department>{
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public Department queryByName(String name){
		return departmentMapper.queryByName(name);
	}
	
}
