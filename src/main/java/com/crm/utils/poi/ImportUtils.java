package com.crm.utils.poi;

import java.util.ArrayList;
import java.util.List;

import com.crm.controller.admin.bo.CustomBO;


public class ImportUtils {
	/**
	  * 客户文档定义
	  */
	public static List<CustomBO> getCustomBOList(List<List<String>> list){
		List<CustomBO> cbos = new ArrayList<CustomBO>();
		for(List<String> l:list){
			CustomBO cbo = new CustomBO();
			cbo.setLocationName(l.get(0));
			cbo.setName(l.get(1));
			cbo.setTelephone(l.get(2));
			cbo.setQq(l.get(3));
			cbo.setEmail(l.get(4));
			cbo.setWechat(l.get(5));
			cbo.setSkype(l.get(6));
			cbo.setLinkedin(l.get(7));
			cbo.setCompany(l.get(8));
			cbo.setWebsize(l.get(9));
			cbo.setCustomAddress(l.get(10));
			cbo.setRemark(l.get(11));
			cbo.setTypeName(l.get(12));
			cbos.add(cbo);
		}
		
		return cbos;
	}
}
