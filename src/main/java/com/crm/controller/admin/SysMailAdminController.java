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

import com.crm.entity.SysMail;
import com.crm.service.SysMailService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/sysmail")
public class SysMailAdminController {
	@Autowired
	private SysMailService sysMailService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<SysMail> pu = sysMailService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "sysmail/sysmails";
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		SysMail sysmail = sysMailService.queryById(id);
		model.addAttribute("sysmail",sysmail);
		return "sysmail/sysmail";
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		SysMail sysmail = sysMailService.queryById(id);
		model.addAttribute("sysmail",sysmail);
		return "sysmail/update_sysmail";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(SysMail sysmail,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		SysMail m = sysMailService.queryByEmail(sysmail.getEmail());
		if(m == null || (m != null && sysmail.getEmail().equals(m.getEmail()) )){
			int count = sysMailService.updateSelective(sysmail);
			if(count > 0){
				returnMap.put("code", 0);
				returnMap.put("msg", "成功！很好地完成了提交。");
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！邮箱名称已存在。");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "sysmail/add_sysmail";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(SysMail sysmail,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		SysMail m = sysMailService.queryByEmail(sysmail.getEmail());
		if(m == null){
			sysmail.setCreateTime(StringUtils.getDateToLong(new Date()));
			sysmail.setRemark("");
			int count = sysMailService.saveSelect(sysmail);
			if(count > 0){
				returnMap.put("code", 0);
				returnMap.put("msg", "成功！很好地完成了提交。");
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！邮箱名称已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	
	
	
	
	@RequestMapping("/delete/{id}")
	public @ResponseBody Map<String,Object> delete(@PathVariable String id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		int count = sysMailService.delete(id);
		if(count > 0){
			returnMap.put("code", 0);
			returnMap.put("msg", "成功！很好地完成了提交。");
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
}
