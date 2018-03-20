package com.crm.dao;

import java.util.List;


import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Custom;
import tk.mybatis.mapper.common.Mapper;


public interface CustomMapper extends Mapper<Custom>{
	List<DataStat> getCustomStatList();
	List<CustomBO> getCustomList(Custom param);
	List<CustomBO> getCustomListByIn(@Param("ids") List<String> ids);
	Custom queryByEmail(@Param("email") String email);
	Integer queryCount();
	List<DataStat> getStatListByBaidu();
}
