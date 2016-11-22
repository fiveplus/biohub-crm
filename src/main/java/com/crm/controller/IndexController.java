package com.crm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.utils.PageCode;
import com.crm.utils.solr.SolrBean;
import com.crm.utils.solr.SolrServer;


@Controller
@RequestMapping("/site")
public class IndexController {
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request,Model model){
		return "main";
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
	
}
