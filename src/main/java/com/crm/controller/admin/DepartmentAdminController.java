package com.crm.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.controller.admin.bo.AdditionalParameters;
import com.crm.controller.admin.bo.Item;
import com.crm.controller.admin.bo.TreeRespBO;
import com.crm.entity.Department;
import com.crm.entity.DeptPermission;
import com.crm.entity.Permission;
import com.crm.entity.User;
import com.crm.service.DepartmentService;
import com.crm.service.DeptPermissionService;
import com.crm.service.PermissionService;
import com.crm.utils.PageCode;
import com.crm.utils.StringUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/dept")
public class DepartmentAdminController {
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private DeptPermissionService deptPermissionService;
	
	@RequestMapping("/list/{page}")
	public String list(@PathVariable int page,HttpServletRequest request,Model model){
		PageInfo<Department> pu = departmentService.queryPageListByWhere(null, page, 10);
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		return "dept/depts";
	}
	
	@RequestMapping("/select/{id}")
	public String select(@PathVariable String id,HttpServletRequest request,Model model){
		Department department = departmentService.queryById(id);
		model.addAttribute("department",department);
		return "dept/dept";
	}
	
	@RequestMapping("/upt/{id}")
	public String upt(@PathVariable String id,HttpServletRequest request,Model model){
		Department department = departmentService.queryById(id);
		model.addAttribute("department",department);
		return "dept/update_dept";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String,Object> update(Department department,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String, Object>();
		Department d = departmentService.queryByName(department.getName());
		if(d == null || (d != null && department.getName().equals(d.getName()))){
			int count = departmentService.updateSelective(department);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！部门名称已存在。");
			returnMap.put("code", 4);
		}
		
		return returnMap;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "dept/add_dept";
	}
	
	@RequestMapping("/save")
	public @ResponseBody Map<String,Object> save(Department department,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		Department d = departmentService.queryByName(department.getName());
		if(d == null){
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			department.setCreateTime(StringUtils.getDateToLong(new Date()));
			department.setUpdateCount(0);
			department.setCreateUser(user.getUserName());
			int count = departmentService.saveSelect(department);
			if(count > 0){
				returnMap.put("msg", "成功！很好地完成了提交。");
				returnMap.put("code", 0);
			}else{
				returnMap.put("msg", "错误！请进行一些更改。");
				returnMap.put("code", 4);
			}
		}else{
			returnMap.put("msg", "错误！部门名称已存在!");
			returnMap.put("code", 4);
		}
		return returnMap;
	}
	
	@RequestMapping("/perlist/{id}")
	public String perlist(@PathVariable String id,HttpServletRequest request,Model model){
		Department department = departmentService.queryById(id);
		model.addAttribute("department",department);
		return "dept/dept_permissions";
	}
	
	@RequestMapping("/perlist.json")
	public @ResponseBody TreeRespBO perlist_json(String pid,String did,HttpServletRequest request,Model model){
		List<Permission> perlist = permissionService.getChildPermission(did);
		List<Permission> list = permissionService.getPermissionByParentId(pid);
		TreeRespBO tree = new TreeRespBO();
		List<Item> boItemList = new ArrayList<Item>();
		if(null != list && list.size() > 0){
			for(Permission p:list){
				Item item = new Item();
				//查询子节点数量
				int child_count = permissionService.getCountByParentId(p.getId());
				item.setName(p.getName());
				if(child_count > 0){
					item.setType("folder");
					AdditionalParameters adp = new AdditionalParameters();
					adp.setId(p.getId());
					item.setAdditionalParameters(adp);
				}else{
					AdditionalParameters adp = new AdditionalParameters();
					adp.setId(p.getId());
					item.setAdditionalParameters(adp);
					for(Permission per:perlist){
						if(per.getId().equals(p.getId())){
							adp.setItemSeleted(true);
							break;
						}
					}
					
					item.setType("item");
				}
				boItemList.add(item);
			}
		}
		tree.setData(boItemList);
		tree.setStatus("OK");
		return tree;
	}
	
	@RequestMapping("/savepers")
	public @ResponseBody Map<String,Object> savepers(String did,String pids,HttpServletRequest request,Model model){
		Map<String,Object> returnMap = new HashMap<String,Object>();
		//权限清除
		deptPermissionService.deletePermissionByDeptId(did);
		if(pids != null && !pids.equals("")){
			//权限保存
			String[] ids = pids.split(",");
			for(String id:ids){
				DeptPermission dp = new DeptPermission();
				dp.setDeptId(did);
				dp.setPermissionId(id);
				int count = deptPermissionService.saveSelect(dp);
				System.out.println("dp:"+count);
			}
		}
		returnMap.put("code", 0);
		returnMap.put("msg", "成功！很好地完成了提交。");
		
		return returnMap;
	}
	
	
	
}
