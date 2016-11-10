package com.crm.controller.admin;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.controller.admin.bo.LogBO;
import com.crm.entity.Log;
import com.crm.entity.User;
import com.crm.service.LogService;
import com.crm.service.UserService;

@Controller
public class IndexAdminController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("/admin/login")
	public String login(String loginName,String password,HttpServletRequest request,HttpServletResponse response,Model model){
		
		if(loginName == null || password == null) 
			return "login";
		String remember = request.getParameter("remember");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName,password,true);
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
			model.addAttribute("error",error);
			return "login";
		}else{
			if(remember!=null && remember.equals("true")){
	        	addCookie(response,"loginName", loginName);
	        	addCookie(response,"password", password);
	        }else{
	        	removeCookie(response,"loginName");
	        	removeCookie(response,"password");
	        }
			return "redirect:/admin/index";
		}
	}
	
	@RequestMapping("/admin")
	public String admin(HttpServletRequest request,Model model){
		return "login";
	}
	
	@RequestMapping("/admin/index")
	public String index(HttpServletRequest request,Model model){
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("admin")){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			List<LogBO> logs = logService.getLogList(user.getId(), 5);
			Log param = new Log();
			int logCount = logService.queryCount(null);
			model.addAttribute("logs",logs);
			model.addAttribute("logCount",logCount);
			return "index";
		}else{
			return "redirt:/admin/login";
		}
		
	}
	
	@RequestMapping("/admin/logout")
	public String logout(RedirectAttributes redirectAttributes){
		//使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
        return "login";
	}
	
	/**
	 * 创建Cookie
	 * @param name Cookie名称
	 * @param value Cookie值
	 */
	public void addCookie(HttpServletResponse response,String name,Object value){
		Cookie c = new Cookie(name,String.valueOf(value));
	    c.setMaxAge(60*60*24*7);
	    c.setPath("/");
        response.addCookie(c);
	}
	/**
	 * 移除Cookie
	 * @param name Cookie名称
	 */
	public void removeCookie(HttpServletResponse response,String name){
		Cookie c = new Cookie(name,null);
	    c.setMaxAge(0);
	    c.setPath("/");
        response.addCookie(c);
	}
}
