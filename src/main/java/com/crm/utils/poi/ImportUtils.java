package com.crm.utils.poi;

import java.util.ArrayList;
import java.util.List;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.ProjectBO;


public class ImportUtils {
	
	/**
	 * 项目文档模板定义
	 * 项目名称（唯一）   邮箱（唯一）
	 */
	public static List<ProjectBO> getProjectBOList(List<List<String>> list){
		List<ProjectBO> pls = new ArrayList<ProjectBO>();
		for(List<String> l:list){
			ProjectBO pbo = new ProjectBO();
			pbo.setName(l.get(0));
			pbo.setCustomName(l.get(1));
			pbo.setTelephone(l.get(2));
			pbo.setEmail(l.get(3));
			pbo.setQq(l.get(4));
			pbo.setWechat(l.get(5));
			pbo.setSkype(l.get(6));
			pbo.setLinkedin(l.get(7));
			pbo.setCompany(l.get(8));
			pbo.setWebsize(l.get(9));
			
			pbo.setLocationName(l.get(10));
			
			pbo.setDomainName(l.get(11));
			pbo.setBrief(l.get(12));
			pbo.setStage(l.get(13));
			pbo.setTrait(l.get(14));
			pbo.setDemand(l.get(15));
			pbo.setRate(l.get(16));
			pbo.setTypeName(l.get(17));
			
			pbo.setCustomType(l.get(18));
			
			pls.add(pbo);
		}
		return pls;
	}
	
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
