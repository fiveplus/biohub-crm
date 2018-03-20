package com.crm.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name="sys_dept_permission")
public class DeptPermission implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
	private String id;
	@Column
	private String permissionId;
	@Column
	private String deptId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	
	
}
