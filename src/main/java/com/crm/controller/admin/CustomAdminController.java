package com.crm.controller.admin;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.crm.utils.poi.ExcelUtils;
import com.crm.utils.poi.ExportUtils;
import com.crm.utils.poi.ImportUtils;
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
		
		Custom c = customService.queryByEmail(custom.getEmail());
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
		List<Project> projects = projectService.queryByCustomId(id);
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
		Custom c = customService.queryByEmail(custom.getEmail());
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
		String name = request.getParameter("name");
		String createUser = request.getParameter("createUser");
		String dateRangePicker = request.getParameter("dateRangePicker");
		List<CustomBO> models = null;
		if(checks == null || (checks!=null && checks.equals(""))){
			param.setStatus(Resource.Y);
			Map<String,Long> betweens= null;
			if(dateRangePicker != null && !dateRangePicker.equals("")){
				betweens = StringUtils.getBetweenTime(dateRangePicker);
			}
			if(betweens != null){
				param.setStartTime(betweens.get("beforeTime"));
				param.setEndTime(betweens.get("afterTime"));
			}
			param.setName(name);
			param.setCreateUser(createUser);
			
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
	
	@RequestMapping("/upload")
	public @ResponseBody Map<String,Object> upload(HttpServletRequest request,@RequestParam MultipartFile file) throws IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		//手工导入
		int total = 0;
		String fileName = file.getOriginalFilename();
		if (fileName == null || "".equals(fileName))  
        {  
			returnMap.put("code", 4);
            returnMap.put("msg", "错误！文件名不存在。");
        }else{
        	String fileEx = fileName.substring(fileName.lastIndexOf("."));
        	if(fileEx.equals(".xls")|| fileEx.equals(".xlsx")){
        		InputStream input = file.getInputStream();
            	List<List<String>> ls = ExcelUtils.readExcel(input);
            	if(ls != null){
            		List<CustomBO> cbos = ImportUtils.getCustomBOList(ls);
            		String temp = "";
            		int i = 0;
            		for(CustomBO cbo:cbos){
            			i++;
            			//TODO 客户EMAIL转换小写
    					cbo.setEmail(cbo.getEmail().toLowerCase());
    					CustomLocation cl = customLocationServie.queryByName(cbo.getLocationName());
    					CustomType ct = customTypeService.queryByName(cbo.getTypeName());
    					Custom cus = customService.queryByEmail(cbo.getEmail());
    					if(cl == null){
    						temp += "客户区域["+cbo.getName()+"]不存在;";
    					}
    					if(ct == null){
    						temp += "客户类型["+cbo.getLocationName()+"]不存在;";
    					}
    					if(cus != null){
    						temp += "客户E-mail["+cbo.getEmail()+"]已存在;";
    					}
    					if(temp.equals("")){
    						Custom c = new Custom();
    						c = new Custom();
    						c.setLocationId(cl.getId());;
    						c.setTypeId(ct.getId());
    						c.setName(cbo.getName());
    						c.setTelephone(cbo.getTelephone());
    						c.setEmail(cbo.getEmail());
    						c.setQq(cbo.getQq());
    						c.setWechat(cbo.getQq());
    						c.setSkype(cbo.getSkype());
    						c.setLinkedin(cbo.getLinkedin());
    						c.setCompany(cbo.getCompany());
    						c.setWebsize(cbo.getWebsize());
    						c.setDeptId(user.getDeptId());
    						c.setCreateTime(StringUtils.getDateToLong(new Date()));
    						c.setModifyTime(c.getCreateTime());
    						c.setCreateUser(user.getUserName());
    						c.setStatus(Resource.Y);
    						int count = customService.saveSelect(c);
    						if(count > 0){
    							//导入成功，总数++
    							total++;
    						}
    					}else{
    						//记录错误信息
    						temp += "行数" + (i+1) + "：" + temp;
    						break;
    					}
            		}
            		if(temp.equals("")){
            			returnMap.put("code", 0);
                        returnMap.put("msg", "成功！很好的完成了"+total+"次提交！");
            		}else{
            			returnMap.put("code", 4);
                        returnMap.put("msg", "错误！"+temp+"成功提交"+total+"次");
            		}
            	}else{
            		returnMap.put("code", 4);
                    returnMap.put("msg", "错误！上传文件格式错误！");
            	}
        	}
        }
		
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
