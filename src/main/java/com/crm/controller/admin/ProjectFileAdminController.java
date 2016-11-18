package com.crm.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.crm.controller.admin.bo.ProjectFileBO;
import com.crm.entity.ProjectFile;
import com.crm.entity.User;
import com.crm.service.ProjectFileService;
import com.crm.utils.DesUtil;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/file")
public class ProjectFileAdminController {
	
	@Autowired
	private ProjectFileService projectFileService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		String projectId = request.getParameter("projectId");
		
		PageInfo<ProjectFileBO> pu = projectFileService.queryFileByProjectId(projectId, page);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		model.addAttribute("projectId",projectId);
		
		
		return "project/files/file_list";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(HttpServletRequest request,@RequestParam("file") MultipartFile[] files) throws Exception {
		//文件夹路径  
        String filePath = request.getSession().getServletContext().getRealPath("/") + "uploads/";  
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		Map<String,Object> returnMap = new HashMap<String,Object>();
		String projectId = request.getParameter("projectId");
		String msg = "";
		int code = 0;
		if(projectId != null&&!projectId.equals("")){
			if(files!=null&&files.length>0){
				for(int i = 0;i<files.length;i++){
					MultipartFile file = files[i];
					String fileName = file.getOriginalFilename();
					ProjectFile pf = projectFileService.queryByName(fileName);
					if(pf == null){
						saveFile(file,request,filePath);
						pf = new ProjectFile();
						pf.setCreateTime(StringUtils.getDateToLong(new Date()));
						pf.setCreateUser(user.getId());
						pf.setDownloadCount(0);
						pf.setName(fileName);
						pf.setUrl(DesUtil.encryptPath(filePath+fileName));
						pf.setProjectId(projectId);
						projectFileService.saveSelect(pf);
					}else{
						code = 4;
						msg += "文件["+fileName+"]已存在;";
						break;
					}
				}
				if(msg.equals("")){
					msg = "成功！很好的完成了提交。";
					code = 0;
				}
			}else{
				msg = "错误！请先选择文件。";
				code = 4;
			}
		}else{
			msg = "错误！参数错误。";
			code = 4;
		}
		returnMap.put("code", code);
		returnMap.put("msg", msg);
		return returnMap;
	}
	
	private boolean saveFile(MultipartFile file,HttpServletRequest request,String path){
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
				//文件保存路径  
                String filePath = path + file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(filePath));
                return true; 
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String desurl,HttpServletRequest request,HttpServletResponse response,Model model) throws IOException, Exception{
		String path = DesUtil.decryptPath(desurl);
		File file = new File(path);
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                                          headers, HttpStatus.CREATED);
	}
	
	
}
