package com.crm.dao;

import java.util.List;

import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Project;
import com.github.abel533.mapper.Mapper;

public interface ProjectMapper extends Mapper<Project>{
	public List<DataStat> getProjectStatList();
}
