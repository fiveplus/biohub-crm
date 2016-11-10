package com.crm.dao;

import java.util.List;

import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Custom;
import com.github.abel533.mapper.Mapper;

public interface CustomMapper extends Mapper<Custom>{
	public List<DataStat> getCustomStatList();
}
