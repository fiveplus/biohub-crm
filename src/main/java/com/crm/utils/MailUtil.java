package com.crm.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtil {
	private static String host = "smtp.exmail.qq.com";
	private static String username = "shenwu.zhang@bridgebiomed.com";
	//private static String from = "shenwu.zhang@bridgebiomed.com";
	private static String password = "linuxBridge0610";
	private static String nick = "Linux System Administrator";
	private static String charset = "utf-8";
	
	private static String nick_company = "百桥国际(BRIDGEBIO)";
	
	public static boolean sendMail(String userName,List<String> to,String subject,String body,List<String> filepath) throws EmailException {
		return sendMail(username,password,userName,to,subject,body,filepath);
	}
	
	public static boolean companySendMail(String username,String password,List<String> to,String subject,String body,List<String> filepath) throws EmailException {
		return sendMail(username,password,nick_company,to,subject,body,filepath);
	}
	
	
	public static boolean sendMail(String username,String password,String nickName,List<String> to,String subject,String body,List<String> filepath) throws EmailException {
		//参数修饰
		if(body == null){
			body = "";
		}
		if(subject == null){
			subject = "无主题";
		}
		HtmlEmail email = new HtmlEmail();
		email.setSSLOnConnect(true); //SSL加密
		email.setStartTLSEnabled(true); //TLS加密
		
		email.setHostName(host);
		email.setAuthentication(username, password);
		email.setFrom(username,nickName);
		if(to != null && to.size() > 0){
			for(String address:to){
				email.addTo(address);
			}
		}else{
			return false;
		}
		
		email.setCharset(charset);
		email.setSubject(subject);
		email.setHtmlMsg(body);
		
		
		if(filepath != null && filepath.size() > 0){
			for(String path:filepath){
				//创建附件
				String name = path.substring(path.lastIndexOf("/")+1);
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(path);
				attachment.setDescription(EmailAttachment.ATTACHMENT);
				attachment.setDescription("");
				attachment.setName(name);
				email.attach(attachment);
			}
		}else{
			System.out.println("no file.");
		}
		email.send();
		return true;
	}

	public static void main(String[] args) throws EmailException{
		List<String> to = new ArrayList<String>();
		to.add("shenwu.zhang@bridgebiomed.com");
		boolean flag = sendMail("shenwu",to, "Test Email", "Test Message ：中文信息", null);
		System.out.println(flag);
	}
}
