package com.crm.utils;

import java.util.Date;

import com.crm.entity.Custom;
import com.crm.entity.Project;
import com.crm.entity.User;

/***
 * 系统日志信息生成帮助类
 * @author hack
 *
 * @param <T>
 */
public class LogUtil<T> {
	public enum LogType{
		SELECT,INSERT,UPDATE,DELETE
	}
	
	public enum LogObject{
		Custom,User,Project;
		public static LogObject getLogObject(Object obj){
			return valueOf(obj.getClass().getSimpleName());
		}
	}
	
	public String getLog(User user,T t,Date date,LogType logType){
		//String time = StringUtil.getDatetToString(date);
		String time = "";
		String log = "";
		String name = t.getClass().getSimpleName();
		switch (LogObject.getLogObject(t)) {
		case Custom:
			log = ""+time+" ["+user.getUserName()+"] " + logType + " "+name+"["+((Custom)t).getName()+"],Affected Row 1.";
			break;
		case User:
			log = ""+time+" ["+user.getUserName()+"] " + logType + " "+name+"["+((User)t).getUserName()+"],Affected Row 1.";
			break;
		case Project:
			log = ""+time+" ["+user.getUserName()+"] " + logType + " "+name+"["+((Project)t).getName()+"],Affected Row 1.";
			break;
		default:
			break;
		}
		return log;
	}
	
	public static void main(String[] args) {
		String log = new LogUtil<Custom>().getLog(new User(),new Custom(), new Date(), LogType.UPDATE);
		System.out.println(log);
	}
	
	
}
