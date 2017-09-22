package com.crm.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Custom;
import com.github.abel533.mapper.Mapper;

public interface CustomMapper extends Mapper<Custom>{
	public List<DataStat> getCustomStatList();
	public List<CustomBO> getCustomList(Custom param);
	public List<CustomBO> getCustomListByIn(@Param("ids") List<String> ids);
	public Custom queryByEmail(@Param("email") String email);
	public Integer queryCount();
	public List<DataStat> getStatListByBaidu();
}
