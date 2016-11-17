package com.crm.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tbl_project_domain")
public class ProjectDomain {
	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	@Column
	private String name;
	@Column
	private String englishName;
	@Column
	private String englishShort;
	@Column
	private String information;
	@Column
	private Long createTime;
	@Column
	private String parentId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getEnglishShort() {
		return englishShort;
	}
	public void setEnglishShort(String englishShort) {
		this.englishShort = englishShort;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
