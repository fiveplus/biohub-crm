package com.crm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.entity.User;
import com.crm.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("admin/login")
	public String login(String loginName,String password,HttpServletRequest request,Model model){
		System.out.println(loginName);
		User user = userService.getUserByLoginNameAndPassword(loginName, password);
		model.addAttribute("user",user);
		return "index";
	}
	
	@RequestMapping("admin")
	public String index(HttpServletRequest request,Model model){
		return "login";
	}
	
	@RequestMapping("admin/logout")
	public String logout(RedirectAttributes redirectAttributes){
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
        return "login";
	}
	
}
