package com.crm.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name="sys_mail")
public class SysMail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
	private String id;
	@Column
	private String email;
	@Column
	private Long createTime;
	@Column
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
