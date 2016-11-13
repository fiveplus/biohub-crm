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

import com.crm.entity.CustomLocation;
import com.crm.service.CustomLocationService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/cuslocation")
public class CustomLocationAdminController {
	@Autowired
	private CustomLocationService customLocationService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<CustomLocation> pu = customLocationService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "custom/locations";
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		CustomLocation location = customLocationService.queryById(id);
		model.addAttribute("location",location);
		return "custom/location";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "custom/add_location";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(CustomLocation location,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		CustomLocation param = new CustomLocation();
		param.setName(location.getName());
		CustomLocation l = customLocationService.queryOne(param);
		if(l == null){
			location.setCreateTime(StringUtils.getDateToLong(new Date()));
			int count = customLocationService.saveSelect(location);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！区域已存在!");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		CustomLocation location = customLocationService.queryById(id);
		model.addAttribute("location",location);
		return "custom/update_location";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(CustomLocation location,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		CustomLocation param = new CustomLocation();
		param.setName(location.getName());
		CustomLocation l = customLocationService.queryOne(param);
		if(l == null || (l != null && location.getName().equals(l.getName()))){
			int count = customLocationService.updateSelective(location);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！区域已存在!");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	
	
	
}
