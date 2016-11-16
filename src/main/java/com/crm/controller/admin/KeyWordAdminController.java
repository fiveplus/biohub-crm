package com.crm.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.entity.KeyWord;
import com.crm.service.KeyWordService;
import com.crm.utils.PageCode;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/keyword")
public class KeyWordAdminController  {
	@Autowired
	private KeyWordService keyWordService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<KeyWord> pu = keyWordService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "keyword/keywords";
	}
	
	
	
}
