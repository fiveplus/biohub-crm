package com.crm.utils.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


/**
 * 谷歌地图API
 * @author hack
 *
 */
public class LngAndLatUtil {
	public static Map<String,String> getLngAndLat(String addr){
		String lat = "";
		String lng = "";
		String address = null;
		try {
			address = java.net.URLEncoder.encode(addr,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+address;
		URL googleMapURL = null;
        URLConnection httpsConn = null;
        // 进行转码
        try {
            googleMapURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        try {
			httpsConn = (URLConnection)googleMapURL.openConnection();
			if(httpsConn != null){
				InputStreamReader insr = new InputStreamReader(httpsConn.getInputStream());
				BufferedReader br = new BufferedReader(insr);
				String data = null;
				if((data = br.readLine()) != null){
					String[] retList = data.split(",");
					if (retList.length > 2 && ("200".equals(retList[0]))) {
                        lat = retList[2];
                        lng = retList[3];
                    }
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        Map<String, String> map = new HashMap<String, String>();
        map.put("lat", new String(lat));
        map.put("lng", new String(lng));
        return map;
	}
	
	public static void main(String[] args) {
		getLngAndLat("日本东京");
	}
}
