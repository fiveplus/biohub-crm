package com.crm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.entity.User;
import com.crm.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("admin/login")
	public String login(String loginName,String password,HttpServletRequest request,Model model){
		User user = userService.getUserByLoginNameAndPassword(loginName, password);
		model.addAttribute("user",user);
		return "index";
	}
	
	@RequestMapping("admin")
	public String index(HttpServletRequest request,Model model){
		return "login";
	}
	
}
