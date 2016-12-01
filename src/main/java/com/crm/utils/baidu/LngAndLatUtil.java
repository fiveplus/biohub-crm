package com.crm.utils.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class LngAndLatUtil {
	//TODO B064a4ea88112dd0912c374b911d62df
	private static final String API_KEY = "B064a4ea88112dd0912c374b911d62df";
	
	public static Map<String,String> getLngAndLat(String addr){
		String address = "";
        String lat = "";
        String lng = "";
        try {  
            address = java.net.URLEncoder.encode(addr,"UTF-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } 
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
                +"ak="+API_KEY+"&output=json&address=%s",address);
        URL myURL = null;
        URLConnection httpsConn = null;  
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {}
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                	System.out.println(data);
                	if(data.indexOf("\"lat\":") > -1){
                		lat = data.substring(data.indexOf("\"lat\":") 
                            + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                            lng = data.substring(data.indexOf("\"lng\":") 
                            + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                	}
                }
                insr.close();
            }
        } catch (IOException e) { }
        Map<String, String> map = new HashMap<String, String>();
        map.put("lat", new String(lat));
        map.put("lng", new String(lng));
        return map;
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Map<String,String> map = getLngAndLat("北京东城");
		long end = System.currentTimeMillis();
		System.out.println((end-start)+"毫秒");
		System.out.println(map.get("lat")+","+map.get("lng"));
	}
	
	
	
}
