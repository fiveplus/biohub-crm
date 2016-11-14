package com.crm.realm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.auth.AuthenticationException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.crm.controller.admin.bo.LogBO;
import com.crm.controller.admin.bo.PermissionBO;
import com.crm.controller.admin.bo.UserBO;
import com.crm.entity.Log;
import com.crm.entity.Permission;
import com.crm.entity.User;
import com.crm.service.LogService;
import com.crm.service.PermissionService;
import com.crm.service.UserService;

public class UserRealm extends AuthorizingRealm{
	
	@Resource
	private UserService userService;
	
	@Resource
	private PermissionService permissionService;
	
	@Resource
	private LogService logService;
	
	/** 
     * 为当前登录的Subject授予角色和权限 
     * @see  经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see  经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     * @see  个人感觉若使用了Spring3.1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache 
     * @see  比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache 
     */  
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentLoginName = (String)super.getAvailablePrincipal(principals);  
        //List<String> roleList = new ArrayList<String>();  
        //List<String> permissionList = new ArrayList<String>();  
        List<PermissionBO> pbos = new ArrayList<PermissionBO>();
        //从数据库中获取当前登录用户的详细信息
        User user = userService.getUserByLoginName(currentLoginName);
        if(null != user){
        	//实体类User中包含有用户角色的实体类信息
        	//授权存储用户信息
        	String deptId = user.getDeptId();
        	
        	if(deptId != null){
        		List<Permission> parentMenu = permissionService.getParentMenu();
        		List<Permission> pers = permissionService.getChildPermission(deptId);
        		for(Permission menu:parentMenu){
        			PermissionBO pbo = new PermissionBO();
        			pbo.setPermission(menu);
    	        	pbo.setPers(new ArrayList<Permission>());
    	        	pbos.add(pbo);
        		}
        		for(PermissionBO pbo:pbos){
    	        	for(Permission per:pers){
    	        		if(per.getParentId().equals(pbo.getPermission().getId())&&per.getIsMenu().equals("Y")){
    	        			pbo.getPers().add(per);
    	        		}
    	        	}
    	        }
        		//反向遍历清除空元素
    	        for(int i = pbos.size()-1;i>=0;i--){
    	        	PermissionBO pbo = pbos.get(i);
    	        	if(pbo.getPers()== null || pbo.getPers().size() == 0){
    	        		pbos.remove(pbo);
    	        	}
    	        }
    	        //用户设权
    	        user.setPers(pers);
    	        
    	        List<UserBO> users = userService.getUserList();
    	        
    	        Session session = SecurityUtils.getSubject().getSession();
    	        session.setAttribute("user", user);
    	        session.setAttribute("menus", pbos);
    	        session.setAttribute("users", users);
    	        
    	        //个人日志加载
    			List<LogBO> logs = logService.getLogList(user.getId(), 5);
    			Log param = new Log();
    			param.setUserId(user.getId());
    			int logCount = logService.queryCount(param);
    			session.setAttribute("logs",logs);
    			session.setAttribute("logCount",logCount);
    	        
    	        
        		//认证
        		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        		simpleAuthorInfo.addRole("admin");
            	System.out.println("已为用户["+currentLoginName+"]赋予了[admin]角色和[admin:manage]权限");
            	return simpleAuthorInfo;
        	}else{
        		return null;
        	}
        }else{
        	try {
				throw new AuthenticationException();
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址  
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
		return null;
	}
	
	/** 
     * 验证当前登录的Subject 
     * @see  经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
     */  
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken)
			throws org.apache.shiro.authc.AuthenticationException {
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken; 
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)  
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
        
        User user = userService.getUserByLoginName(token.getUsername());
        
        if(user == null){
        	throw new UnknownAccountException();
        }
        //判断账号是否被锁定
        if (user.getLocked() > 0) {  
            // 抛出 帐号锁定异常  
            throw new LockedAccountException();  
        }
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配  
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
                user.getLoginName(), // 用户名  
                user.getPassword(), // 密码  
                ByteSource.Util.bytes(user.getCredentialsSalt()),// salt=username+salt  
                getName() // realm name  
        );  
        return authenticationInfo; 
        
	}
	
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {  
        super.clearCachedAuthorizationInfo(principals);  
    } 
	
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {  
        super.clearCachedAuthenticationInfo(principals);  
    } 
	
	public void clearCache(PrincipalCollection principals) {  
        super.clearCache(principals);  
    } 
	
	public void clearAllCachedAuthorizationInfo() {  
	    getAuthorizationCache().clear();  
	}  
	  
	public void clearAllCachedAuthenticationInfo() {  
	    getAuthenticationCache().clear();  
	}  
	  
	public void clearAllCache() {  
	    clearAllCachedAuthenticationInfo();  
	    clearAllCachedAuthorizationInfo();  
	}  
	
	
}
