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

import com.crm.entity.ProjectType;
import com.crm.service.ProjectTypeService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/projectype")
public class ProjectTypeAdminController {
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<ProjectType> pu = projectTypeService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "project/projectypes";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "project/add_projectype";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(ProjectType projectType,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		ProjectType pt = projectTypeService.queryByName(projectType.getName());
		if(pt == null){
			projectType.setCreateTime(StringUtils.getDateToLong(new Date()));
			projectType.setInformation("");
			int count = projectTypeService.saveSelect(projectType);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！项目类型已存在。");
			returnMap.put("code", 4);
		}
		
		
		return returnMap;
	}
	
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		ProjectType projectType = projectTypeService.queryById(id);
		model.addAttribute("projectType",projectType);
		return "project/update_projectype";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(ProjectType projectType,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		ProjectType pt = projectTypeService.queryByName(projectType.getName());
		if(pt == null || (pt != null && projectType.getName().equals(pt.getName()))){
			int count = projectTypeService.updateSelective(projectType);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！项目类型已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	
	
	
}
