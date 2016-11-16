package com.crm.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.entity.KeyWord;
import com.crm.service.KeyWordService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
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
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "keyword/add_keyword";
	}
	
	@RequestMapping("save")
	public @ResponseBody Map<String,Object> save(KeyWord keyword,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		KeyWord k = keyWordService.queryByName(keyword.getName());
		if(k == null){
			keyword.setUseCount(0);
			keyword.setCreateTime(StringUtils.getDateToLong(new Date()));
			int count = keyWordService.saveSelect(keyword);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！关键词已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		KeyWord keyword = keyWordService.queryById(id);
		model.addAttribute("keyword",keyword);
		return "keyword/update_keyword";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(KeyWord keyword,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		KeyWord k = keyWordService.queryByName(keyword.getName());
		if(k == null || (k != null && keyword.getName().equals(k.getName()))){
			int count = keyWordService.updateSelective(keyword);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！关键词已存在。");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	
	
}
