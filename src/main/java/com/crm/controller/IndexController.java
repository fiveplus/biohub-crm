package com.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/site")
public class IndexController {
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request,Model model){
		return "main";
	}
}
