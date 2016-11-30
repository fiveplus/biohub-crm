package com.crm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.controller.admin.bo.ProjectBO;
import com.crm.entity.Project;
import com.crm.service.ProjectService;
import com.crm.utils.PageCode;
import com.crm.utils.solr.SolrBean;
import com.crm.utils.solr.SolrServer;


@Controller
@RequestMapping("/site")
public class IndexController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request,Model model){
		return "main";
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		String c = request.getParameter("c");
		if(c != null) c = new String(c.getBytes("iso-8859-1"), "utf-8");
		ProjectBO project = projectService.getProjectById(id);
		model.addAttribute("project",project);
		model.addAttribute("c",c);
		return "project";
	}
	
	@RequestMapping("/q")
	public String query(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		String content = request.getParameter("c");
		if(content != null) content = new String(content.getBytes("iso-8859-1"), "utf-8");
		String p = request.getParameter("p");
		int page = 1;
		if(p != null && !p.equals("")){
			page = Integer.parseInt(p);
		}
		if(page <= 0){
			page = 1;
		}
		if(content == null){
			content="";
		}
		if(content.equals("")){
			return "main";
		}
		SolrServer server = new SolrServer();
		List<SolrBean> list = server.query(content,page);
		if(list == null || list.size()==0){
			page = page > 0 ? page - 1 : 1;
			list = server.query(content, page);
		}
		
		double time = (server.getTime() / 1000.0);
		
		long count = server.getNumber();
		int pageCount = 0;
		if(count%10==0){
			pageCount = (int)(count/10); 
		}else{
			pageCount = (int)(count/10) + 1; 
		}
		
		PageCode pc = new PageCode(page, pageCount);
		
		model.addAttribute("beans", list);
		model.addAttribute("c", content);
		model.addAttribute("p", page);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("pagelist",pc.getPageList());
		model.addAttribute("count",count);
		model.addAttribute("time",time);
		
		return "query";
	}
	
	@RequestMapping("/h")
	public String support(HttpServletRequest request,Model model){
		return "help";
	}
	
	@RequestMapping("/map")
	public String map(HttpServletRequest request,Model model){
		return "map";
	}
	
}
