package com.crm.utils.aliyun;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LngAndLatUtil {
	public static final String API_KEY = "";
	
	public static Map<String,String> getLngAndLat(String address){
		String lat = "";
		String lng = "";
		String host = "http://jisujwddz.market.alicloudapi.com";
	    String path = "/geoconvert/addr2coord";
	    String method = "GET";
	    String appcode = "0e5b0281309d44d2b133cddf37f1b450";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("address", address);
	    querys.put("type", "baidu");
		try{
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
	    	String result = EntityUtils.toString(response.getEntity());
	    	ObjectMapper mapper = new ObjectMapper();
	    	Map<?,?> map = mapper.readValue(result, Map.class);
	    	Map<String,String> res = (Map<String, String>) map.get("result");
	    	lat = res.get("lat");
	    	lng = res.get("lng");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("lat", lat);
		map.put("lng",lng);
		return map;
	}
	
	public static void main(String[] args) {
		Map<String,String> map = getLngAndLat("武汉");
		System.out.println("lat:"+map.get("lat"));
		System.out.println("lng:"+map.get("lng"));
	}
}
