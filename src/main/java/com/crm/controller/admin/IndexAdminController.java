package com.crm.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crm.controller.admin.bo.CustomBO;
import com.crm.controller.admin.bo.LogBO;
import com.crm.controller.admin.bo.PermissionBO;
import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.Custom;
import com.crm.entity.Department;
import com.crm.entity.KeyWord;
import com.crm.entity.Log;
import com.crm.entity.Permission;
import com.crm.entity.ProjectDomain;
import com.crm.entity.ProjectType;
import com.crm.entity.User;
import com.crm.service.CustomService;
import com.crm.service.DepartmentService;
import com.crm.service.KeyWordService;
import com.crm.service.LogService;
import com.crm.service.PermissionService;
import com.crm.service.ProjectDomainService;
import com.crm.service.ProjectTypeService;
import com.crm.service.UserService;

@Controller
public class IndexAdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private LogService logService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private CustomService customService;

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private KeyWordService keyWordService;

	@Autowired
	private ProjectDomainService projectDomainService;

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/admin/login")
	public String login(String loginName, String password,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		HttpSession session = request.getSession();
		if (loginName == null || password == null)
			return "login";
		String remember = request.getParameter("remember");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(loginName,
				password);

		String error = null;
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
		} catch (ExcessiveAttemptsException e) {
			error = "登录失败多次，账户锁定10分钟";
		} catch (AuthenticationException e) {
			error = "其他错误：" + e.getMessage();
		}

		if (error != null) {
			model.addAttribute("error", error);
			return "login";
		} else {
			// TODO 成功后权限组装
			List<PermissionBO> pbos = new ArrayList<PermissionBO>();
			// 从数据库中获取当前登录用户的详细信息
			User user = userService.getUserByLoginName(loginName);
			if (null != user) {
				// 实体类User中包含有用户角色的实体类信息
				// 授权存储用户信息
				String deptId = user.getDeptId();

				if (deptId != null) {
					List<Permission> parentMenu = permissionService
							.getParentMenu();
					List<Permission> pers = permissionService
							.getChildPermissionByUserId(user.getId());
					// TODO 无权从部门权限里加载
					if (pers == null || (pers != null && pers.size() == 0)) {
						pers = permissionService
								.getChildPermissionByDeptId(deptId);
					}
					for (Permission menu : parentMenu) {
						PermissionBO pbo = new PermissionBO();
						pbo.setPermission(menu);
						pbo.setPers(new ArrayList<Permission>());
						pbos.add(pbo);
					}
					for (PermissionBO pbo : pbos) {
						for (Permission per : pers) {
							if (per.getParentId().equals(
									pbo.getPermission().getId())
									&& per.getIsMenu().equals("Y")) {
								pbo.getPers().add(per);
							}
						}
					}
					// 反向遍历清除空元素
					for (int i = pbos.size() - 1; i >= 0; i--) {
						PermissionBO pbo = pbos.get(i);
						if (pbo.getPers() == null || pbo.getPers().size() == 0) {
							pbos.remove(pbo);
						}
					}
					// 用户设权
					user.setPers(pers);

					List<UserBO> users = userService.getUserList();
					Custom param = new Custom();
					List<CustomBO> customs = customService.getCustomList(param);
					List<ProjectType> types = projectTypeService.queryAll();
					List<KeyWord> keywords = keyWordService.queryAll();
					List<ProjectDomain> parents = projectDomainService
							.getParentList();
					String keystr = "";
					for (KeyWord kw : keywords) {
						keystr += kw.getName() + ",";
					}
					keystr = keystr.substring(0, keystr.length() - 1);

					List<Department> depts = departmentService.queryAll();

					session.setAttribute("user", user);
					session.setAttribute("menus", pbos);
					session.setAttribute("users", users);
					session.setAttribute("customs", customs);
					session.setAttribute("depts", depts);

					session.setAttribute("types", types);
					session.setAttribute("keywords", keystr);
					session.setAttribute("parents", parents);

					// 个人日志加载
					List<LogBO> logs = logService.getLogList(user.getId(), 5);
					int logCount = logService.queryCountByUserId(user.getId());
					session.setAttribute("logs", logs);
					session.setAttribute("logCount", logCount);
				}
			}

			if (remember != null && remember.equals("true")) {
				addCookie(response, "loginName", loginName);
				addCookie(response, "password", password);
			} else {
				removeCookie(response, "loginName");
				removeCookie(response, "password");
			}
			return "redirect:/admin/index";
		}
	}

	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, Model model) {
		return "login";
	}

	@RequestMapping("/admin/index")
	public String index(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("admin")) {
			return "index";
		} else {
			return "redirt:/admin/login";
		}

	}

	@RequestMapping("/admin/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		redirectAttributes.addFlashAttribute("message", "您已安全退出");
		return "login";
	}

	@RequestMapping("/admin/msg")
	public @ResponseBody
	Map<String, Object> msg(HttpServletRequest request, Model model) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("code", 4);
		returnMap.put("msg", "授权验证失败。");
		return returnMap;
	}

	/**
	 * 创建Cookie
	 * 
	 * @param name
	 *            Cookie名称
	 * @param value
	 *            Cookie值
	 */
	public void addCookie(HttpServletResponse response, String name,
			Object value) {
		Cookie c = new Cookie(name, String.valueOf(value));
		c.setMaxAge(60 * 60 * 24 * 7);
		c.setPath("/");
		response.addCookie(c);
	}

	/**
	 * 移除Cookie
	 * 
	 * @param name
	 *            Cookie名称
	 */
	public void removeCookie(HttpServletResponse response, String name) {
		Cookie c = new Cookie(name, null);
		c.setMaxAge(0);
		c.setPath("/");
		response.addCookie(c);
	}
}
