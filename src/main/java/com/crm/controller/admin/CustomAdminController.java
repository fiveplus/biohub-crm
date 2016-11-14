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

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Custom;
import com.crm.service.CustomService;
import com.crm.service.UserService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/custom")
public class CustomAdminController {
	
	@Autowired
	private CustomService customService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,Custom param,HttpServletRequest request,Model model){
		if(param == null) param = new Custom();
		String dateRangePicker = request.getParameter("dateRangePicker");
		Map<String,Long> betweens= null;
		if(dateRangePicker != null && !dateRangePicker.equals("")){
			betweens = StringUtils.getBetweenTime(dateRangePicker);
		}
		if(betweens != null){
			param.setStartTime(betweens.get("beforeTime"));
			param.setEndTime(betweens.get("afterTime"));
		}
		PageInfo<CustomBO> pu = customService.getCustomList(page, param);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		
		model.addAttribute("dateRangePicker");
		model.addAttribute("param",param);
		
		return "custom/customs";
	}
	
	
	@RequestMapping("/stat.json")
	public @ResponseBody Map<String,Object> customStat(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int count = 0;
		List<DataStat> cusStats = customService.getCustomStatList();
		for(int i = 0;i<cusStats.size();i++){
			count += cusStats.get(i).getCount();
		}
		
		returnMap.put("cusStats", cusStats);
		returnMap.put("count", count);
		
		return returnMap;
	}
	
}
