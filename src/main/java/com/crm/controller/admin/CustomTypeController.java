package com.crm.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.CustomType;
import com.crm.service.CustomTypeService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/customtype")
public class CustomTypeController {
	@Autowired
	private CustomTypeService customTypeService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<CustomType> pu = customTypeService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "custom/types";
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		CustomType type = customTypeService.queryById(id);
		model.addAttribute("type",type);
		return "custom/type";
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		CustomType type = customTypeService.queryById(id);
		model.addAttribute("type",type);
		return "custom/update_type";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(CustomType type,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		CustomType param = new CustomType();
		param.setName(type.getName());
		CustomType t = customTypeService.queryOne(param);
		if(t == null || (t != null && type.getName().equals(t.getName()))){
			int count = customTypeService.updateSelective(type);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！客户类型已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "custom/add_type";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(CustomType type,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		CustomType param = new CustomType();
		param.setName(type.getName());
		CustomType t = customTypeService.queryOne(param);
		if(t == null){
			type.setCreateTime(StringUtils.getDateToLong(new Date()));
			int count = customTypeService.saveSelect(type);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！客户类型已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
}
