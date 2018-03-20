package com.crm.dao;


import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.entity.Department;
import tk.mybatis.mapper.common.Mapper;

public interface DepartmentMapper extends Mapper<Department>{
	Department queryByName(@Param("name") String name);
}
