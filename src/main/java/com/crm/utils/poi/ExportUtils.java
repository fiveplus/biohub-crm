package com.crm.utils.poi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.ProjectBO;
import com.crm.controller.admin.bo.UserBO;

public class ExportUtils {
	
	public static XSSFWorkbook exportProjectBO(List<ProjectBO> models) throws Exception{
		if(models == null || models.size() == 0){
			//throw new Exception("暂无数据导出");
		}
		List<ExcelMapping> ems = new ArrayList<>();
        Map<Integer, List<ExcelMapping>> map = new LinkedHashMap<>();
        XSSFWorkbook book = null;
        //一个ems 为一行标题
        
        ems.add(new ExcelMapping("项目编号","projectNum",0));
        ems.add(new ExcelMapping("项目名称","name",0));
        ems.add(new ExcelMapping("项目所有人","customName",0));
        ems.add(new ExcelMapping("联系电话","telephone",0));
        ems.add(new ExcelMapping("邮箱","email",0));
        ems.add(new ExcelMapping("QQ","qq",0));
        ems.add(new ExcelMapping("微信","wechat",0));
        ems.add(new ExcelMapping("Skype","skype",0));
        ems.add(new ExcelMapping("Linked-in","linkedin",0));
        ems.add(new ExcelMapping("单位","company",0));
        ems.add(new ExcelMapping("网址","websize",0));
        ems.add(new ExcelMapping("客户区域","locationName",0));
        ems.add(new ExcelMapping("项目领域","domainName",0));
        ems.add(new ExcelMapping("项目简介","brief",0));
        ems.add(new ExcelMapping("项目阶段","stage",0));
        ems.add(new ExcelMapping("项目特点","trait",0));
        ems.add(new ExcelMapping("项目需求","demand",0));
        ems.add(new ExcelMapping("项目分类级别","rate",0));
        ems.add(new ExcelMapping("项目类别","typeName",0));
        ems.add(new ExcelMapping("客户类别","customType",0));
        
        map.put(0, ems);
        try {
        	book = ExcelUtils.createExcelFile(models, map,"项目数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return book;
	}
	
	public static XSSFWorkbook exportCustomBO(List<CustomBO> models) throws Exception {
		if(models == null || models.size() == 0){
			throw new Exception("暂无数据导出");
		}
		List<ExcelMapping> ems = new ArrayList<>();
        Map<Integer, List<ExcelMapping>> map = new LinkedHashMap<>();
        XSSFWorkbook book = null;
        //一个ems 为一行标题
        ems.add(new ExcelMapping("客户区域", "locationName", 0));
        ems.add(new ExcelMapping("客户姓名","name",0));
        ems.add(new ExcelMapping("联系电话","telephone",0));
        ems.add(new ExcelMapping("QQ","qq", 0));
        ems.add(new ExcelMapping("电子邮箱","email", 0));
        ems.add(new ExcelMapping("微信","wechat", 0));
        ems.add(new ExcelMapping("Skype","skype", 0));
        ems.add(new ExcelMapping("Linked-in","linkedin", 0));
        ems.add(new ExcelMapping("单位","company", 0));
        ems.add(new ExcelMapping("网址","websize", 0));
        ems.add(new ExcelMapping("客户住址","customAddress", 0));
        ems.add(new ExcelMapping("备注","remark", 0));
        ems.add(new ExcelMapping("客户类别","typeName",0));
      
        map.put(0, ems);
        try {
        	book = ExcelUtils.createExcelFile(models, map,"客户数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return book;
	}
}
