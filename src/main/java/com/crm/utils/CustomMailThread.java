package com.crm.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

import com.crm.controller.admin.bo.CustomBO;


public class CustomMailThread extends Thread{
	private List<CustomBO> customs;
	private String title;
	private String content;
	
	private String username;
	private String password;
	
	public CustomMailThread(String username,String password,List<CustomBO> customs,String title,String content){
		this.customs = customs;
		this.title = title;
		this.content = content;
		
		this.username = username;
		this.password = password;
	}
	
	
	public void run() {
		for(CustomBO c:customs){
			try{
				String moban = "Dear "+c.getName()+"<br /><br />";
				String text = moban + content;
				List<String> to = new ArrayList<String>();
				to.add(c.getEmail());
				MailUtil.companySendMail(username,password,to, title, text, null);
			}catch(EmailException e){
				e.printStackTrace();
			}
		}
	}
}
