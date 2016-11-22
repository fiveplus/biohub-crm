package com.crm.utils.solr;

import org.apache.solr.client.solrj.beans.Field;

public class SolrBean {
	@Field("id")
	private String id;
	@Field("name")
	private String name;
	@Field("brief")
	private String brief;
	@Field("project_tag")
	private String projectTag;
	@Field("demand")
	private String demand;
	@Field("follow_user")
	private String followUser;
	@Field("update_time")
	private Long updateTime;
	
	private String text;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		if(brief !=null){
			if(brief.length() > 100){
				this.text = brief.substring(0,100)+"...";
			}else{
				this.text = brief;
			}
		}
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getProjectTag() {
		return projectTag;
	}

	public void setProjectTag(String projectTag) {
		this.projectTag = projectTag;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getFollowUser() {
		return followUser;
	}

	public void setFollowUser(String followUser) {
		this.followUser = followUser;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
}
