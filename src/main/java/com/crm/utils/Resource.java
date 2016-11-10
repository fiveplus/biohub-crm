package com.crm.utils;

import java.util.ArrayList;
import java.util.List;

import com.crm.utils.KeyValue;

/**
 * 系统静态资源
 * @author hack
 *
 */
public class Resource {
	
	public static String contextPath = "/web-crm";
	public static String URL_IMAGES = "/common/images/";
	public static String URL_CSS = "/common/css/";
	public static String URL_JS = "/common/js/";
	public static String CURRENT_SYSTEM = "/admin";
	
	public static final String Y = "Y";
	public static final String D = "D";
	public static final String N = "N";
	
	public static List<KeyValue> STAGES = new ArrayList<KeyValue>(){{
		add(new KeyValue("初创期", "初创期（试验阶段）"));
		add(new KeyValue("成长期", "成长期（中试、临床前阶段）"));
		add(new KeyValue("成熟期", "成熟期（申报、临床阶段）"));
	}};
	
	public static List<KeyValue> DEMANDS = new ArrayList<KeyValue>(){{
		add(new KeyValue("技术转让", "技术转让"));
		add(new KeyValue("平台服务", "平台服务"));
		add(new KeyValue("投融资服务", "投融资服务"));
		add(new KeyValue("人才基金申报", "人才基金申报"));
		add(new KeyValue("工商代理", "工商代理"));
	}};
	
	public static List<KeyValue> METHODS = new ArrayList<KeyValue>(){{
		add(new KeyValue("电话", "电话"));
		add(new KeyValue("邮件", "邮件"));
		add(new KeyValue("见面", "见面"));
		
	}};
	
	public static List<KeyValue> RATES = new ArrayList<KeyValue>(){{
		add(new KeyValue("A", "A"));
		add(new KeyValue("B", "B"));
		add(new KeyValue("C", "C"));
		add(new KeyValue("D", "D"));
		add(new KeyValue("E", "E"));
	}};
	
	
	
	public static String image(String file){
		StringBuffer imgBf = new StringBuffer(contextPath + URL_IMAGES);
		imgBf.append(file);
		return imgBf.toString();
	}
	
	public static String js(String file){
		StringBuffer jsBf = new StringBuffer(contextPath + URL_JS);
		jsBf.append(file);
		return jsBf.toString();
	}
	
	public static String css(String file) {
		StringBuffer jsBf = new StringBuffer(contextPath + URL_CSS);
		jsBf.append(file);
		return jsBf.toString();
	}
	
	public static String curSystem(){
		return CURRENT_SYSTEM;
	}
	
	public static String boot(){
		return contextPath;
	}
}
