package com.crm.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.LogBO;
import com.crm.entity.User;
import com.crm.service.LogService;
import com.crm.utils.PageCode;
import com.crm.utils.Resource;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/log")
public class LogAdminController {
	@Autowired
	private LogService logService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<LogBO> pu = logService.getLogListNoParam(page);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "log/logs";
	}
	
	@RequestMapping("/readall")
	public @ResponseBody Map<String,Object> readall(HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String, Object>();
		int count = logService.updateLogByUserIdAndIsRead(Resource.Y, user.getId());
		if(count > 0){
			returnMap.put("code", 0);
		}else{
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
}
