package com.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Param;

import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.KeyWord;
import tk.mybatis.mapper.common.Mapper;

public interface KeyWordMapper extends Mapper<KeyWord>{
	KeyWord queryByName(@Param("name") String name);
	List<DataStat> getStatListByKeyWord();
}
