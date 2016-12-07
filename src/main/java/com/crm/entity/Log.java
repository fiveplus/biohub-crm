package com.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="sys_log")
public class Log implements Serializable{
	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	@Column
	private String userId;
	@Column
	private Long createTime;
	@Column
	private String information;
	@Column
	private String customId;
	@Column
	private String projectId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	
}
