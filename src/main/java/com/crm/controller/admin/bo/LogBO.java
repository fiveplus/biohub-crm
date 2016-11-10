package com.crm.controller.admin.bo;

import javax.persistence.Column;
import javax.persistence.Id;

public class LogBO {
	@Id
	private String id;
	@Column
	private String userName;
	@Column
	private Long createTime;
	@Column
	private String information;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	
}
