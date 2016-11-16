package com.crm.controller.admin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.DataStat;
import com.crm.entity.Custom;
import com.crm.entity.CustomLocation;
import com.crm.entity.CustomType;
import com.crm.entity.Project;
import com.crm.entity.User;
import com.crm.service.CustomLocationService;
import com.crm.service.CustomService;
import com.crm.service.CustomTypeService;
import com.crm.service.LogService;
import com.crm.service.ProjectService;
import com.crm.service.UserService;
import com.crm.utils.LogUtil.LogObject;
import com.crm.utils.PageCode;
import com.crm.utils.Resource;
import com.crm.utils.StringUtils;
import com.crm.utils.LogUtil.LogType;
import com.crm.utils.poi.ExportUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/custom")
public class CustomAdminController {
	
	@Autowired
	private CustomService customService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomLocationService customLocationServie;
	
	@Autowired
	private CustomTypeService customTypeService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private LogService logService;
	
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,Custom param,HttpServletRequest request,Model model){
		if(param == null) param = new Custom();
		param.setStatus(Resource.Y);
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
	
	@RequestMapping("/dellist/{page}")
	public String dellist(@PathVariable int page,Custom param,HttpServletRequest request,Model model){
		if(param == null) param = new Custom();
		param.setStatus(Resource.D);
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
		
		return "custom/customdels";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		List<CustomLocation> locations = customLocationServie.queryAll();
		List<CustomType> types = customTypeService.queryAll();
		model.addAttribute("locations",locations);
		model.addAttribute("types",types);
		return "custom/add_custom";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(Custom custom,HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Date now = new Date();
		
		//TODO 修改系统大小写情况
		custom.setEmail(custom.getEmail().toLowerCase());
		
		Custom param = new Custom();
		param.setEmail(custom.getEmail());
		Custom c = customService.queryOne(param);
		if(c == null){
			custom.setCreateTime(StringUtils.getDateToLong(now));
			custom.setModifyTime(StringUtils.getDateToLong(now));
			custom.setCreateUser(user.getUserName());
			custom.setDeptId(user.getDeptId());
			custom.setStatus(Resource.Y);
			int count = customService.saveSelect(custom);
			if(count > 0){
				logService.saveLog(user, now, custom, LogType.INSERT, LogObject.Custom, custom.getId());
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！客户E-mail已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		Custom custom = customService.queryById(id);
		Project param = new Project();
		param.setCustomId(custom.getId());
		List<Project> projects = projectService.queryListByWhere(param);
		model.addAttribute("custom",custom);
		model.addAttribute("projects",projects);
		return "custom/custom";
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		Custom custom = customService.queryById(id);
		List<CustomLocation> locations = customLocationServie.queryAll();
		List<CustomType> types = customTypeService.queryAll();
		
		model.addAttribute("custom",custom);
		model.addAttribute("locations",locations);
		model.addAttribute("types",types);
		return "custom/update_custom";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(Custom custom,HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Date now = new Date();
		//TODO 修改系统大小写情况
		custom.setEmail(custom.getEmail().toLowerCase());
		Custom param = new Custom();
		param.setEmail(custom.getEmail());
		Custom c = customService.queryOne(param);
		if(c == null || (c != null && custom.getEmail().equals(c.getEmail()))){
			custom.setModifyTime(StringUtils.getDateToLong(now));
			int count = customService.updateSelective(custom);
			if(count > 0){
				logService.saveLog(user, now, custom, LogType.UPDATE, LogObject.Custom, custom.getId());
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！客户E-mail已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/del/{id}")
	public @ResponseBody Map<String,Object> del(@PathVariable String id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Custom c = customService.queryById(id);
		String msg = "";
		if(c.getStatus().equals(Resource.D)){
			msg = "错误！删除申请已经提交。";
		}else if(c.getStatus().equals(Resource.N)){
			msg = "错误！该客户已经删除。";
		}
		if(msg.equals("")){
			c.setStatus(Resource.D);
			int count = customService.updateSelective(c);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", msg);
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/delete/{id}")
	public @ResponseBody Map<String,Object> delete(@PathVariable String id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Custom c = customService.queryById(id);
		String msg = "";
		if(c.getStatus().equals(Resource.Y)){
			msg = "错误！该客户已经恢复。";
		}else if(c.getStatus().equals(Resource.N)){
			msg = "错误！该客户已经删除。";
		}
		if(msg.equals("")){
			c.setStatus(Resource.N);
			int count = customService.updateSelective(c);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", msg);
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	@RequestMapping("/recover/{id}")
	public @ResponseBody Map<String,Object> recover(@PathVariable String id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		Custom c = customService.queryById(id);
		String msg = "";
		if(c.getStatus().equals(Resource.Y)){
			msg = "错误！该客户已经恢复。";
		}else if(c.getStatus().equals(Resource.N)){
			msg = "错误！该客户已经删除。";
		}
		if(msg.equals("")){
			c.setStatus(Resource.Y);
			int count = customService.updateSelective(c);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", msg);
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	@RequestMapping("/export")
	public @ResponseBody Map<String,Object> export(Custom param,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		Map<String,Object> returnMap = new HashMap<String,Object>();
		if(param != null) param = new Custom();
		String checks = request.getParameter("checks");
		List<CustomBO> models = null;
		if(checks == null || (checks!=null && checks.equals(""))){
			param.setStatus(Resource.Y);
			models = customService.getCustomList(param);
		}else{
			models = customService.getCustomListByIn(checks);
		}
		
		response.reset();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssms");
		String dateStr = sdf.format(new Date());
		// 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" +dateStr+".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook = ExportUtils.exportCustomBO(models);
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
        	returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
            e.printStackTrace();
        }
        returnMap.put("code", 0);
        returnMap.put("msg", "成功！很好地完成了提交。");
        return returnMap;
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
