package com.crm.utils.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * 谷歌地图API
 * @author hack
 *
 */
public class LngAndLatUtil {
	
	private static String API_KEY = "AIzaSyDQV2hSf0YlfHmw8queU2RDO-LOFPvcFvI";
	
	public static Map<String,String> getLngAndLat(String addr){
		String lat = "";
		String lng = "";
		try{
			String _url = "https://maps.googleapis.com/maps/api/geocode/json?address="+addr+"&key="+API_KEY;
			URL url = new URL(_url);
			InetSocketAddress address = new InetSocketAddress("127.0.0.1",1086);
			Proxy proxy = new Proxy(Proxy.Type.SOCKS, address); // Socket 代理
		    HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);// 设置代理访问
		    InputStreamReader in = new InputStreamReader(connection.getInputStream());
		    BufferedReader reader = new BufferedReader(in);
		    String str = "";
		    String s = "";
		    ObjectMapper mapper = new ObjectMapper();
		    while ((s = reader.readLine()) != null) {
		    	 if (s != null) {
		    		 str += s;
		    		 //System.out.println(s);
		    	 }
		    }
		    
		    Map<?,?> map = mapper.readValue(str, Map.class);
		    List list = (List) map.get("results");
		    Map<?,?> result = (Map<?, ?>) list.get(0);
		    Map<?,?> geometry = (Map<?, ?>) result.get("geometry");
		    Map<?,?> location = (Map<?, ?>) geometry.get("location");
		    lat = location.get("lat").toString();
		    lng = location.get("lng").toString();
		    Map<String,String> m = new HashMap<String, String>();
		    m.put("lat", lat);
		    m.put("lng", lng);
		    return m;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		Map<String,String> map = getLngAndLat("都灵");
		System.out.println("lat:"+map.get("lat"));
		System.out.println("lng:"+map.get("lng"));
	}
}
