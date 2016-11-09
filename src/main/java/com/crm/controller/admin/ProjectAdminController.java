package com.crm.controller.admin;

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
import com.crm.service.ProjectService;
import com.crm.utils.Resource;

@Controller
@RequestMapping("/")
public class ProjectAdminController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("admin/project/stat.json")
	public @ResponseBody Map<String,Object> projectStat(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		List<DataStat> proStats = projectService.getProjectStatList();
		for(int i = 0;i<proStats.size();i++){
			proStats.get(i).setColor(Resource.COLORS[i]);
		}
		int count = projectService.queryCount(null);
		
		returnMap.put("proStats", proStats);
		returnMap.put("count", count);
		
		return returnMap;
	}
	
}
