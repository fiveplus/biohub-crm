package com.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class User implements Serializable{
	@Id
	private String id;
	@Column
	private String loginName;
	@Column
	private String userName;
	@Column
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
