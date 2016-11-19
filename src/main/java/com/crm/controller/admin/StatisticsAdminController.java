package com.crm.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProcessBO;
import com.crm.entity.User;
import com.crm.service.ProjectProcessService;
import com.crm.service.ProjectService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/admin/statistics")
public class StatisticsAdminController {
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectProcessService processService;
	
	@RequestMapping("/select_time")
	public String selectTime(HttpServletRequest request,Model model){
		return "statistics/select_time";
	}
	
	@RequestMapping("/process.json")
	public @ResponseBody Map<String,Object> process(String dateRangePicker,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		Map<String,Long> betweens = null;
		if(dateRangePicker != null && !dateRangePicker.equals("")){
			betweens = StringUtils.getBetweenTime(dateRangePicker);
		}
		Long startTime = betweens.get("beforeTime");
		Long endTime = betweens.get("afterTime");
		List<DataStat> datas = processService.getProcessStatListByCreateTime(startTime, endTime);
		
		returnMap.put("datas", datas);
		
		return returnMap;
	}
	
	@RequestMapping("/projs.json")
	public @ResponseBody Map<String,Object> projs(String dateRangePicker,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		Map<String,Long> betweens = null;
		if(dateRangePicker != null && !dateRangePicker.equals("")){
			betweens = StringUtils.getBetweenTime(dateRangePicker);
		}
		Long startTime = betweens.get("beforeTime");
		Long endTime = betweens.get("afterTime");
		List<DataStat> datas = projectService.getProjectStatListByCreateTime(startTime, endTime);
		
		returnMap.put("datas", datas);
		
		return returnMap;
	}
	
	@RequestMapping("/processes/{page}")
	public String processes(@PathVariable int page,String dateRangePicker,HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Long> betweens = null;
		if(dateRangePicker != null && !dateRangePicker.equals("")){
			betweens = StringUtils.getBetweenTime(dateRangePicker);
		}
		Long startTime = betweens.get("beforeTime");
		Long endTime = betweens.get("afterTime");
		
		PageInfo<ProcessBO> pu = processService.getProcessListByCreateTime(page, user.getId(), startTime, endTime);
		PageCode pc = new PageCode(page, pu.getPages());
		
		model.addAttribute("dateRangePicker",dateRangePicker);
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "/statistics/project/process_list";
	}
	
	@RequestMapping("/statistics")
	public String statistics(String dateRangePicker,HttpServletRequest request,Model model){
		Map<String,Long> betweens = null;
		if(dateRangePicker != null && !dateRangePicker.equals("")){
			betweens = StringUtils.getBetweenTime(dateRangePicker);
		}
		Long startTime = betweens.get("beforeTime");
		Long endTime = betweens.get("afterTime");
		//数据查询
		List<DataStat> projectDatastats = projectService.getProjectStatListByCreateTime(startTime, endTime);
		List<DataStat> followDatastats = processService.getProcessStatListByCreateTime(startTime, endTime);
		
		int projectCount = projectService.getProjectCount(startTime, endTime);
		int projectUpdateCount = 0;
		//TODO 求和
		for(DataStat stat:followDatastats){
			projectUpdateCount += stat.getCount();
		}

		model.addAttribute("projectDatastats",projectDatastats);
		model.addAttribute("followDatastats",followDatastats);
		model.addAttribute("projectCount",projectCount);
		model.addAttribute("projectUpdateCount",projectUpdateCount);
		model.addAttribute("dateRangePicker",dateRangePicker);
		
		return "statistics/statistics";
	}
	
}
