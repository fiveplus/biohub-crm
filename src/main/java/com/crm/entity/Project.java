package com.crm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="tbl_project")
public class Project implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")
	private String id;
	@Column
	private String projectNum;
	@Column
	private String typeId;
	@Column
	private String name;
	@Column
	private String rate;
	@Column
	private String customId;
	@Column
	private String domainId;
	@Column
	private String stage;
	@Column
	private String brief;
	@Column
	private String trait;
	@Column
	private String demand;
	@Column
	private Long createTime;
	@Column
	private String createUser;
	@Column
	private String status;
	@Column
	private Integer projectIndex;
	@Column
	private String projectTag;
	@Column
	@OrderBy("DESC")
	private Long updateTime;
	@Column
	private String followUser;
	@Column
	private String chargeUser;
	
	@Transient
	private Long startTime;
	@Transient
	private Long endTime;
	@Transient
	private String parentDomainId;
	@Transient
	private String order;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getProjectIndex() {
		return projectIndex;
	}
	public void setProjectIndex(Integer projectIndex) {
		this.projectIndex = projectIndex;
	}
	public String getProjectTag() {
		return projectTag;
	}
	public void setProjectTag(String projectTag) {
		this.projectTag = projectTag;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public String getFollowUser() {
		return followUser;
	}
	public void setFollowUser(String followUser) {
		this.followUser = followUser;
	}
	public String getChargeUser() {
		return chargeUser;
	}
	public void setChargeUser(String chargeUser) {
		this.chargeUser = chargeUser;
	}
	
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getParentDomainId() {
		return parentDomainId;
	}
	public void setParentDomainId(String parentDomainId) {
		this.parentDomainId = parentDomainId;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}
