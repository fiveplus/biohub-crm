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

import com.crm.controller.admin.bo.ProcessBO;
import com.crm.entity.Project;
import com.crm.entity.ProjectProcess;
import com.crm.entity.User;
import com.crm.service.ProjectProcessService;
import com.crm.service.ProjectService;
import com.crm.service.UserService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.crm.utils.poi.ExcelUtils;
import com.crm.utils.poi.ExportUtils;
import com.crm.utils.poi.ImportUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/process")
public class ProcessAdminController {
	@Autowired
	private ProjectProcessService processService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		String projectId = request.getParameter("projectId");
		
		PageInfo<ProcessBO> pu = processService.queryProcessByProjectId(projectId, page);
		PageCode pc = new PageCode(page, pu.getPages());
		
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		model.addAttribute("projectId",projectId);
		return "project/process/processes";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(ProjectProcess process,HttpServletRequest request,Model model){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String, Object>();
		process.setProcessId(user.getId());
		process.setUserId(user.getId());
		process.setCreateTime(StringUtils.getDateToLong(new Date()));
		int count = processService.saveSelect(process);
		if(count > 0){
			returnMap.put("code", 0);
	        returnMap.put("msg", "成功！很好地完成了提交。");
		}else{
			returnMap.put("msg", "错误！请进行一些更改。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/upload")
	public @ResponseBody Map<String,Object> upload(HttpServletRequest request,@RequestParam MultipartFile file) throws IOException {
		Map<String,Object> returnMap = new HashMap<String, Object>();
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
            		List<ProcessBO> pbos = ImportUtils.getProcessBOList(ls);
            		String temp = "";
            		int i = 0;
            		for(ProcessBO pbo:pbos){
            			i++;
            			/**
            			 * 1.跟进人是否存在
            			 * 3.时间格式
            			 */
            			if(pbo.getProjectName().equals("")){
            				temp += "请填写项目名称;";
            			}
            			if(pbo.getProcessUser().equals("")){
            				temp += "请填写跟进人;";
            			}
            			if(pbo.getCreateTime().equals("")){
            				temp += "请填写跟进时间;";
            			}
            			
            			
            			
            			Project p = projectService.queryByName(pbo.getProjectName());
            			if(p == null){
            				temp += "项目名称["+pbo.getProjectName()+"]不存在;";
            			}
            			User u = userService.getUserByUserName(pbo.getProcessUser());
            			if(u == null){
            				temp += "跟进人["+pbo.getProcessUser()+"]不存在;";
            			}
            			if(temp.equals("")){
            				ProjectProcess pp = new ProjectProcess();
            				pp.setCreateTime(pbo.getCreateTime());
            				pp.setProjectId(p.getId());
            				pp.setUserId(u.getId());
            				pp.setDemand(pbo.getDemand());
            				pp.setInformation(pbo.getInformation());
            				pp.setMethod(pbo.getMethod());
            				pp.setProcessId(u.getId());
            				int count = processService.saveSelect(pp);
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
	
	@RequestMapping("/export")
	public @ResponseBody Map<String,Object> export(ProjectProcess param,HttpServletResponse response,HttpServletRequest request,Model model) throws Exception {
		Map<String,Object> returnMap = new HashMap<String, Object>();
		if(param == null) param = new ProjectProcess();
		String projectId = request.getParameter("projectId");
		List<ProcessBO> models = null;
		models = processService.queryProcessByProjectId(projectId);
		response.reset();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssms");
		String dateStr = sdf.format(new Date());
		// 指定下载的文件名
		response.setHeader("Content-Disposition", "attachment;filename=" +dateStr+".xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        XSSFWorkbook workbook = ExportUtils.exportProcessBO(models);
        
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
	
}
