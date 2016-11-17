package com.crm.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.ProjectDomainBO;
import com.crm.entity.ProjectDomain;
import com.crm.service.ProjectDomainService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/projectdomain")
public class ProjectDomainAdminController {
	@Autowired
	private ProjectDomainService projectDomainService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<ProjectDomainBO> pu = projectDomainService.queryList(page);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "project/domains";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		List<ProjectDomain> parents = projectDomainService.getParentList();
		model.addAttribute("parents",parents);
		return "project/add_domain";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(ProjectDomain projectDomain,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		//ProjectDomain pd = projectDomainService.queryByName(projectDomain.getName());
		//if(pd == null){
			if(projectDomain.getParentId().equals("")) projectDomain.setParentId(null);
			projectDomain.setCreateTime(StringUtils.getDateToLong(new Date()));
			int count = projectDomainService.saveSelect(projectDomain);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		//}else{
		//	returnMap.put("msg", "错误！项目领域已存在。");
		//	returnMap.put("code", 4);
		//}
		return returnMap;
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		ProjectDomain projectDomain = projectDomainService.queryById(id);
		List<ProjectDomain> parents = projectDomainService.getParentList();
		model.addAttribute("parents",parents);
		model.addAttribute("projectDomain",projectDomain);
		return "project/update_domain";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(ProjectDomain projectDomain,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		if(projectDomain.getParentId().equals("")) projectDomain.setParentId(null);
		int count = projectDomainService.updateSelective(projectDomain);
		if(count > 0){
			returnMap.put("msg", "成功！很好地完成了提交。");
			returnMap.put("code", 0);
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	
	
	
}
