package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.KeyWord;
import com.github.abel533.mapper.Mapper;

public interface KeyWordMapper extends Mapper<KeyWord>{
	public KeyWord queryByName(@Param("name") String name);
	public List<DataStat> getStatListByKeyWord();
}
