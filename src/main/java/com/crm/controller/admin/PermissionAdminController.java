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

import com.crm.entity.Permission;
import com.crm.service.PermissionService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/permission")
public class PermissionAdminController {
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<Permission> pu = permissionService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "permission/permissions";
	}
	
	@RequestMapping("select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		Permission permission = permissionService.queryById(id);
		model.addAttribute("permission",permission);
		return "permission/permission";
	}
	
	@RequestMapping("upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		Permission permission = permissionService.queryById(id);
		List<Permission> parents = permissionService.getParentPermission();
		model.addAttribute("permission",permission);
		model.addAttribute("parents",parents);
		return "permission/update_permission";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(Permission permission,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		int count = permissionService.updateSelective(permission);
		if(permission.getParentId().equals("")) permission.setParentId(null);
		if(count > 0){
			returnMap.put("code",0);
			returnMap.put("msg","成功！很好地完成了提交。");
		}else{
			returnMap.put("code",4);
			returnMap.put("msg", "错误！请进行一些更改。");
		}
		return returnMap;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		List<Permission> parents = permissionService.getParentPermission();
		model.addAttribute("parents",parents);
		return "permission/add_permission";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(Permission permission,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Permission p = permissionService.queryById(permission.getId());
		if(p != null){
			returnMap.put("msg", "错误！权限ID已存在。");
			returnMap.put("code", 4);
		}else{
			if(permission.getParentId().equals("")) permission.setParentId(null);
			permission.setCreateTime(StringUtils.getDateToLong(new Date()));
			permission.setImageUrl("");
			int count = permissionService.saveSelect(permission);
			if(count > 0){
				returnMap.put("code", 0);
				returnMap.put("msg", "成功！很好地完成了提交。");
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}
		
		
		
		return returnMap;
	}
	
	
}
