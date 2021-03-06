package com.crm.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tbl_file")
public class ProjectFile implements Serializable{
	@Id
    @KeySql(sql="select replace(uuid(),'-','')",order= ORDER.BEFORE)
	private String id;
	@Column
	private String name;
	@Column
	private String projectId;
	@Column
	private String url;
	@Column
	private Long createTime;
	@Column
	private String createUser;
	@Column
	private Integer downloadCount;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Integer getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
