package com.crm.quartz;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crm.utils.HttpUtils;
import com.crm.utils.PropertiesUtils;
import com.crm.utils.StringUtils;

@Component
public class SolrScheduler{
	
	private Log log = LogFactory.getLog(SolrScheduler.class);
	
	private PropertiesUtils putil = new PropertiesUtils();
	
	@Scheduled(cron="0 0/5 * * * ? ")
	public void work(){
		 try {
			 String url = putil.getProperty("solr.dataimport");
			 HttpUtils.doGet(url, null, "utf8", true);
			 log.info("["+StringUtils.getDatetToString(new Date())+"][Solr]dataimport/fullimport更新成功。");
         } catch (Exception e) {  
        	 log.error("Solr定时全量索引搜索错误。");
         }  
		
	}
	
}
