package com.crm.controller.admin;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Project;
import com.crm.service.ProjectService;
import com.crm.utils.Resource;

@Controller
@RequestMapping("/admin/project")
public class ProjectAdminController {
	
	@Autowired
	private ProjectService projectService;
	
	
	@RequestMapping("/stat.json")
	public @ResponseBody Map<String,Object> projectStat(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int count = 0;
		List<DataStat> proStats = projectService.getProjectStatList();
		for(int i = 0;i<proStats.size();i++){
			count += proStats.get(i).getCount();
		}
		
		returnMap.put("proStats", proStats);
		returnMap.put("count", count);
		
		return returnMap;
	}
	
}
