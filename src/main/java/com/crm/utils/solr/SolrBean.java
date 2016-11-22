package com.crm.utils.solr;

import org.apache.solr.client.solrj.beans.Field;

public class SolrBean {
	@Field
	private String id;
	@Field
	private String name;
	@Field
	private String brief;
	@Field
	private String projectTag;
	@Field
	private String demand;
	
	private String text;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		if(brief !=null){
			if(brief.length() > 200){
				this.text = brief.substring(0,200);
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
	
	
	
}
