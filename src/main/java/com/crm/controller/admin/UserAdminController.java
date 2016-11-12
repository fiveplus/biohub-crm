package com.crm.controller.admin;

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

import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.Department;
import com.crm.entity.User;
import com.crm.service.DepartmentService;
import com.crm.service.UserService;
import com.crm.utils.PageCode;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<UserBO> pu = userService.getUserList(page);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "user/users";
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		UserBO us = userService.getUserById(id);
		model.addAttribute("us",us);
		return "user/user";
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		User us = userService.queryById(id);
		List<Department> departments = departmentService.queryAll();
		model.addAttribute("us",us);
		model.addAttribute("departments",departments);
		return "user/update_user";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(User us,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int count = userService.updateSelective(us);
		if(count > 0){
			returnMap.put("msg", "成功！很好地完成了提交。");
			returnMap.put("code", 0);
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		List<Department> departments = departmentService.queryAll();
		model.addAttribute("departments",departments);
		return "user/add_user";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(User us,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		User u = userService.getUserByLoginName(us.getLoginName());
		int count = 0;
		String message = "成功！用户创建成功，初始化密码为Wuhan2016";
		if(u == null){
			count = userService.saveUser(us);
		}else{
			message = "用户名已存在，请重新输入!";
		}
		
		if(count > 0){
			returnMap.put("msg",message);
			returnMap.put("code", 0);
		}else{
			returnMap.put("msg",message);
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	
	
	
}
