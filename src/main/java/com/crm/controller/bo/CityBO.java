package com.crm.controller.bo;

import java.io.Serializable;

public class CityBO implements Serializable{
	private String name;
	private String lat;
	private String lng;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLat() {
		return lat;
	}
	public void setLnt(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	
}
