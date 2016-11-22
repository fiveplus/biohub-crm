package com.crm.utils.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.BinaryRequestWriter;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.crm.utils.PropertiesUtils;



public class SolrServer {
	public CommonsHttpSolrServer server;
	
	private final int PAGE_SIZE = 10;
	
	private long num;
	private long time;
	
	public SolrServer(){
		try {
			if(server == null){
				String url = new PropertiesUtils().getProperty("solrurl");
				server = new CommonsHttpSolrServer(url);
			}
			server.setSoTimeout(3000);
			server.setParser(new XMLResponseParser());
			server.setConnectionTimeout(1000);
			server.setDefaultMaxConnectionsPerHost(1000);
			server.setMaxRetries(10);
			server.setFollowRedirects(false);
			server.setAllowCompression(true);
			server.setMaxRetries(1);
			server.setRequestWriter(new BinaryRequestWriter());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public List<SolrBean> query(String content,int page){
		List<SolrBean> beans = null;
		try{
			long start_time = System.currentTimeMillis();
			
			SolrQuery query = new SolrQuery();
			query.setQuery("text:"+content);
			query.setHighlight(true);
			query.addHighlightField("name");// 高亮字段
			query.addHighlightField("brief");// 高亮字段
			query.addHighlightField("demand");// 高亮字段
			query.addHighlightField("project_tag");// 高亮字段
			query.setHighlightSimplePre("<font color=\"red\"><b>");// 标记
			query.setHighlightSimplePost("</b></font>");
			query.setHighlightSnippets(2);// 结果分片数，默认为1
			query.setHighlightFragsize(100);// 每个分片的最大长度，默认为100
			//不模糊高亮
			query.set("hl.highlightMultiTerm", false);
				
			query.setStart((page - 1) * PAGE_SIZE);
			query.setRows(PAGE_SIZE); 
			
			QueryResponse resp = server.query(query); 
	        beans = resp.getBeans(SolrBean.class);
	        Map<String, Map<String, List<String>>> map =  resp.getHighlighting();
	        for(SolrBean bean:beans){
	        	List<String> names = map.get(bean.getId()).get("name");
	        	List<String> briefs = map.get(bean.getId()).get("brief");
	        	List<String> demands = map.get(bean.getId()).get("demand");
	        	List<String> tags = map.get(bean.getId()).get("project_tag");
	        	bean.setName(names!=null&&names.size()>0?names.get(0):bean.getName());
	        	bean.setBrief(briefs!=null&&briefs.size()>0?briefs.get(0):bean.getBrief());
	        	bean.setDemand(demands!=null&&demands.size()>0?demands.get(0):bean.getDemand());
	        	bean.setProjectTag(tags!=null&&tags.size()>0?tags.get(0):bean.getProjectTag());
	        }
	        
	     
	        num = resp.getResults().getNumFound();
	        
	        long end_time = System.currentTimeMillis();
	        
	        time = end_time - start_time;
	        
		}catch(Exception e){
			System.err.println("solrc FileNotFoundException:'"+content+"' no found.");
			e.printStackTrace();
		}
		return beans;
	}
	
	public long getNumber(){
		return num;
	}
	
	public long getTime(){
		return time;
	}
	

}
