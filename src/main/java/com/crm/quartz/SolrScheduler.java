package com.crm.quartz;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crm.utils.HttpUtils;
import com.crm.utils.PropertiesUtils;

//@Component
//@Lazy(value=false)
public class SolrScheduler{
	
	//@Scheduled(cron="* */10 * * * ? ")
	public void work(){
		 try {  
			 String url = new PropertiesUtils().getProperty("solr.dataimport");
			 System.out.println(url);
         } catch (Exception e) {  
             e.printStackTrace();  
         }  
		 System.out.println("Solr定时全量索引:" + new Date().toString()); 
		 
		 //String result = HttpUtils.doGet(url, null, "utf8", true);
		 //System.out.println(result);
	}
	
}
