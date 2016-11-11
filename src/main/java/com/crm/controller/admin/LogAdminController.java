package com.crm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.controller.admin.bo.LogBO;
import com.crm.service.LogService;
import com.crm.utils.PageCode;
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
	
}
