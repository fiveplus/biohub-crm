package com.crm.dao;


import org.apache.ibatis.annotations.Param;

import com.crm.entity.Department;
import com.github.abel533.mapper.Mapper;
public interface DepartmentMapper extends Mapper<Department>{
	public Department queryByName(@Param("name") String name);
}
