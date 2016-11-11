package com.crm.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.entity.Department;
import com.crm.entity.Permission;
import com.crm.service.DepartmentService;
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
	public String update(Department department,HttpServletRequest request,Model model){
		int count = departmentService.updateSelective(department);
		if(count > 0){
			model.addAttribute("message","恭喜您，部门修改成功！");
			model.addAttribute("returnURL","admin/dept/list/1");
			return "success";
		}else{
			model.addAttribute("message","很抱歉，部门修改失败!");
			return "error";
		}
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Model model){
		return "dept/add_dept";
	}
	
	@RequestMapping("/save")
	public String save(Department department,HttpServletRequest request,Model model){
		department.setCreateTime(StringUtils.getDateToLong(new Date()));
		department.setUpdateCount(0);
		int count = departmentService.saveSelect(department);
		if(count > 0){
			model.addAttribute("message","恭喜您，部门创建成功!");
			model.addAttribute("returnURL","admin/dept/list/1");
			return "success";
		}else{
			model.addAttribute("message","很抱歉，部门创建失败!");
			return "error";
		}
	}
	
	@RequestMapping("/perlist/{id}/{page}")
	public String perlist(@PathVariable String id,@PathVariable int page,HttpServletRequest request,Model model){
		Department department = departmentService.queryById(id);
		PageInfo<Permission> pu = permissionService.queryPageListByWhere(null, page, 10);
		List<Permission> pers = permissionService.getChildPermission(id);
		//TODO 权限组装
		for(int i = 0;i < pu.getList().size();i++){
			for(Permission per:pers){
				if(per.getId().equals(pu.getList().get(i).getId())){
					per.setFlag(0);
					pu.getList().set(i, per);
					break;
				}
			}
		}
		PageCode pc = new PageCode(page, pu.getPages());
		model.addAttribute("pu",pu);
		model.addAttribute("pc",pc);
		model.addAttribute("department",department);
		return "dept/dept_permissions";
	}
	
	
	
}
