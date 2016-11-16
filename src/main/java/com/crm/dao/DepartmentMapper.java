package com.crm.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.Department;
import com.github.abel533.mapper.Mapper;
@ResponseBody
public interface DepartmentMapper extends Mapper<Department>{
	public Department queryByName(@Param("name") String name);
}
