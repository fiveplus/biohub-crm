package com.crm.controller.admin.bo;

import java.io.Serializable;

public class DataStat implements Serializable{
	private String name;
	private Long count;
	
	public DataStat(){
		super();
	}
	
	public DataStat(String name){
		this.name = name;
	} 
	
	public DataStat(String name,Long count){
		this.name = name;
		this.count = count;
	} 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}

}
