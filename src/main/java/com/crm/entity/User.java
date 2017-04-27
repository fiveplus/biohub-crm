package com.crm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="sys_user")
public class User implements Serializable{
	@Id
	@GeneratedValue(generator="UUID")
	private String id;
	@Column
	private String loginName;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String picture;
	@Column
	private Long loginTime;
	@Column
	@OrderBy("DESC")
	private Long modifyTime;
	@Column
	private String information;
	@Column
	private String deptId;
	@Column
	private String salt;
	@Column
	private int locked; 
	@Column
	private String status;
	@Transient
	private List<Permission> pers;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSalt(){
		return salt;
	}
	public void setSalt(String salt){
		this.salt = salt;
	}
	public int getLocked(){
		return locked;
	}
	public void setLocked(int locked){
		this.locked = locked;
	}
	public String getCredentialsSalt(){
		return loginName + salt;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return status;
	}
	public List<Permission> getPers() {
		return pers;
	}
	public void setPers(List<Permission> pers) {
		this.pers = pers;
	}
	
	
	
}
