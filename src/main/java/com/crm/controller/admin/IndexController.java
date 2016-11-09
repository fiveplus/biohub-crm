package com.crm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		String error = null;
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e){
			error = "用户名/密码错误";
		} catch (ExcessiveAttemptsException e){
			error = "登录失败多次，账户锁定10分钟";
		} catch (AuthenticationException e){
			error = "其他错误：" + e.getMessage();
		}
		if(error != null){
			return "login";
		}else{
			return "index";
		}
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
