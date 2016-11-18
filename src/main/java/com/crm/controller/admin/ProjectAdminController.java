package com.crm.controller.admin;

import java.awt.Color;
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
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crm.controller.admin.bo.DataStat;
import com.crm.controller.admin.bo.ProjectBO;
import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.Custom;
import com.crm.entity.CustomLocation;
import com.crm.entity.CustomType;
import com.crm.entity.KeyWord;
import com.crm.entity.Project;
import com.crm.entity.ProjectDomain;
import com.crm.entity.ProjectType;
import com.crm.entity.User;
import com.crm.service.CustomLocationService;
import com.crm.service.CustomService;
import com.crm.service.CustomTypeService;
import com.crm.service.KeyWordService;
import com.crm.service.LogService;
import com.crm.service.ProjectDomainService;
import com.crm.service.ProjectService;
import com.crm.service.ProjectTypeService;
import com.crm.utils.PageCode;
import com.crm.utils.Resource;
import com.crm.utils.StringUtils;
import com.crm.utils.poi.ExcelUtils;
import com.crm.utils.poi.ExportUtils;
import com.crm.utils.poi.ImportUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/project")
public class ProjectAdminController {
	
	@Autowired
	private KeyWordService keyWordService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ProjectDomainService projectDomainService;
	
	@Autowired
	private ProjectTypeService projectTypeService;
	
	@Autowired
	private CustomService customService;
	
	@Autowired
	private CustomLocationService customLocationService;
	
	@Autowired
	private CustomTypeService customTypeService;
	
	@Autowired
	private LogService logService;
	
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,Project param,HttpServletRequest request,Model model){
		if(param == null) param = new Project();
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
		PageInfo<ProjectBO> pu = projectService.getProjectList(page, param);
		PageCode pc = new PageCode(page, pu.getPages());
		
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		
		model.addAttribute("dateRangePicker",dateRangePicker);
		model.addAttribute("param",param);
		
		//列表参数
		List<ProjectDomain> childs = projectDomainService.getChildList(param.getParentDomainId());
		model.addAttribute("childs",childs);
		
		return "project/projects";
	}
	
	@RequestMapping("/export")
	public @ResponseBody Map<String,Object> export(Project param,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		Map<String,Object> returnMap = new HashMap<String,Object>();
		if(param == null) param = new Project();
		String checks = request.getParameter("checks");
		String name = request.getParameter("name");
		String parentDomainId = request.getParameter("parentDomainId");
		String domainId = request.getParameter("domainId");
		String customId = request.getParameter("customId");
		String createUser = request.getParameter("createUser");
		String rate = request.getParameter("rate");
		String dateRangePicker = request.getParameter("dateRangePicker");
		List<ProjectBO> models = null;
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
			param.setParentDomainId(parentDomainId);
			param.setDomainId(domainId);
			param.setCustomId(customId);
			param.setCreateUser(createUser);
			param.setRate(rate);
			
			models = projectService.getProjectList(param);
		}else{
			models = projectService.getProjectListByIn(checks);
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
        XSSFWorkbook workbook = ExportUtils.exportProjectBO(models);
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
            returnMap.put("code", 0);
            returnMap.put("msg", "成功！很好地完成了提交。");
        } catch (IOException e) {
        	returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
            e.printStackTrace();
        }
        
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
            		List<ProjectBO> pbos = ImportUtils.getProjectBOList(ls);
            		String temp = "";
            		int i = 0;
            		for(ProjectBO pbo:pbos){
            			i++;
            			//TODO 客户EMAIL转换小写
            			pbo.setEmail(pbo.getEmail().toLowerCase());
            			/**
						 * 1.判断项目名称是否重复
						 * 2.判断项目所有人是否存在(根据邮箱判断)
						 * 3.项目领域是否填写正确
						 */
            			if(pbo.getName().equals("")){
            				temp += "请填写项目名称;";
            			}
            			if(pbo.getEmail().equals("")){
							temp += "请填写Email;";
						}
            			Project p = projectService.queryByName(pbo.getName());
            			if(p != null){
            				temp += "项目名称["+pbo.getName()+"]已存在;";
            			}
            			Custom c = customService.queryByEmail(pbo.getEmail());
            			CustomLocation cl = customLocationService.queryByName(pbo.getLocationName());
            			CustomType ct = customTypeService.queryByName(pbo.getCustomType());
            			if(cl == null){
    						temp += "客户类型["+pbo.getCustomType()+"]不存在;";
    					}
    					if(ct == null){
    						temp += "客户区域["+pbo.getLocationName()+"]不存在;";
    					}
    					if(c == null && ct != null && cl != null){
    						//创建客户
    						c = new Custom();
    						c.setName(pbo.getCustomName());
    						c.setTelephone(pbo.getTelephone());
    						c.setEmail(pbo.getEmail());
    						c.setQq(pbo.getQq());
    						c.setWechat(pbo.getWechat());
    						c.setSkype(pbo.getSkype());
    						c.setLinkedin(pbo.getLinkedin());
    						c.setCompany(pbo.getCompany());
    						c.setWebsize(pbo.getWebsize());
    						c.setDeptId(user.getDeptId());
    						c.setLocationId(cl.getId());
    						c.setCreateTime(StringUtils.getDateToLong(new Date()));
    						c.setModifyTime(c.getCreateTime());
    						c.setCreateUser(user.getUserName());
    						c.setStatus(Resource.Y);
    						c.setTypeId(ct.getId());
    						int count = customService.saveSelect(c);
    					}
    					ProjectDomain param = new ProjectDomain();
    					param.setEnglishShort(pbo.getDomainName());
    					ProjectDomain pd = projectDomainService.queryOne(param);
    					if(pd == null){
    						temp += "项目领域["+pbo.getDomainName()+"]填写错误;";
    					}
    					ProjectType pt = projectTypeService.queryByName(pbo.getTypeName());
    					if(pt == null){
    						temp += "项目类别["+pbo.getTypeName()+"]填写错误;";
    					}
    					if(p == null && pd != null && pt != null){
    						Date now = new Date();
    						p = new Project();
    						p.setName(pbo.getName());
							p.setTypeId(pt.getId());
							p.setCustomId(c.getId());
							p.setDomainId(pd.getId());
							p.setBrief(pbo.getBrief());
							p.setStage(pbo.getStage());
							p.setTrait(pbo.getTrait());
							p.setDemand(pbo.getDemand());
							p.setRate(pbo.getRate());
							p.setCreateTime(StringUtils.getDateToLong(now));
							
							p.setUpdateTime(StringUtils.getDateToLong(now));
							
							p.setCreateUser(user.getUserName());
							p.setFollowUser(user.getUserName());
							p.setChargeUser(user.getUserName());
							
							p.setStatus(Resource.Y);
							
							int count = projectService.saveProject(p);
							if(count > 0){
								total++;
							}
    					}else{
    						//记录错误信息
    						temp = "行数" + (i+1) + "：" + temp;
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
            	}
        	}
        }
		return returnMap;
	}
	
	@RequestMapping("/check")
	public @ResponseBody Map<String,Object> check(HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		int count = customService.queryCount();
		if(count > 0){
			returnMap.put("code", 0);
			returnMap.put("msg", "成功！很好的完成了提交。");
		}else{
			returnMap.put("code", 4);
			returnMap.put("msg", "错误!客户不存在。");
		}
		return returnMap;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		String customId = request.getParameter("customId");
		if(customId != null){
			Custom custom = customService.queryById(customId);
			model.addAttribute("custom",custom);
		}
		
		return "project/add_project";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(Project project,HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Project p = projectService.queryByName(project.getName());
		if(p == null){
			Date now = new Date();
			project.setCreateTime(StringUtils.getDateToLong(now));
			project.setCreateUser(user.getUserName());
			project.setChargeUser(user.getUserName());
			project.setFollowUser(user.getUserName());
			project.setStatus(Resource.Y);
			project.setUpdateTime(StringUtils.getDateToLong(now));
			int count = projectService.saveProject(project);
			String[] keywords = project.getProjectTag().split(",");
			//更新词库
			for(String k:keywords){
				k = k.trim();
				KeyWord kw = keyWordService.queryByName(k);
				if(kw == null){
					kw = new KeyWord();
					kw.setName(k);
					kw.setUseCount(1);
					kw.setCreateTime(StringUtils.getDateToLong(now));
					keyWordService.saveSelect(kw);
				}else{
					kw.setUseCount(kw.getUseCount()+1);
					keyWordService.updateSelective(kw);
				}
			}
			if(count > 0){
				returnMap.put("code", 0);
				returnMap.put("msg", "成功！很好的完成了提交。");
			}else{
				returnMap.put("code", 4);
				returnMap.put("msg", "错误!请进行一些修改。");
			}
		}else{
			returnMap.put("code", 4);
			returnMap.put("msg", "错误!项目名称已存在。");
		}
		
		return returnMap;
	}
	
	@RequestMapping("/del/{id}")
	public @ResponseBody Map<String,Object> del(@PathVariable String id,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Project p = projectService.queryById(id);
		String msg = "";
		if(p.getStatus().equals(Resource.D)){
			msg = "错误！删除申请已经提交。";
		}else if(p.getStatus().equals(Resource.N)){
			msg = "错误！该项目已经删除。";
		}
		if(msg.equals("")){
			p.setStatus(Resource.D);
			int count = projectService.updateSelective(p);
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
		Project p = projectService.queryById(id);
		String msg = "";
		if(p.getStatus().equals(Resource.Y)){
			msg = "错误！该项目已经恢复。";
		}else if(p.getStatus().equals(Resource.N)){
			msg = "错误！该项目已经删除。";
		}
		if(msg.equals("")){
			p.setStatus(Resource.N);
			int count = projectService.updateSelective(p);
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
		
		Project p = projectService.queryById(id);
		String msg = "";
		if(p.getStatus().equals(Resource.Y)){
			msg = "错误！该项目已经恢复。";
		}else if(p.getStatus().equals(Resource.N)){
			msg = "错误！该项目已经删除。";
		}
		if(msg.equals("")){
			p.setStatus(Resource.Y);
			int count = projectService.updateSelective(p);
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
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		ProjectBO project = projectService.getProjectById(id);
		List<UserBO> cus = logService.getUserListByCustomId(project.getCustomId());
		List<UserBO> pus = logService.getUserListByProjectId(id);
		model.addAttribute("project",project);
		model.addAttribute("cus",cus);
		model.addAttribute("pus",pus);
		
		return "project/project";
	}
	
	@RequestMapping("/updateInitCustom/{customId}")
	public String updateInitCustom(@PathVariable String customId,HttpServletRequest request,Model model){
		Custom custom = customService.queryById(customId);
		model.addAttribute("custom",custom);
		return "project/custom/update";
	}
	
	@RequestMapping("/customSelect/{customId}")
	public String customSelect(@PathVariable String customId,HttpServletRequest request,Model model){
		Custom custom = customService.queryById(customId);
		model.addAttribute("custom",custom);
		return "project/custom/select";
	}
	
	@RequestMapping("/updateInitProject/{projectId}")
	public String updateInitProject(@PathVariable String projectId,HttpServletRequest request,Model model){
		Project project = projectService.queryById(projectId);
		ProjectDomain domain = projectDomainService.queryById(project.getDomainId());
		model.addAttribute("project",project);
		model.addAttribute("domain",domain);
		return "project/project/update";
	}
	
	@RequestMapping("/projectSelect/{projectId}")
	public String projectSelect(@PathVariable String projectId,HttpServletRequest request,Model model){
		ProjectBO project = projectService.getProjectById(projectId);
		model.addAttribute("project",project);
		return "project/project/select";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(Project project,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		Project p = projectService.queryByName(project.getName());
		if(p == null || (p != null && project.getName().equals(p.getName()))){
			int count = projectService.updateSelective(project);
			if(count > 0){
				/*
				if(project.getRate().equals("A")){
					//TODO 发送邮件
					
				}*/
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！项目名称已存在。");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	
	
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
