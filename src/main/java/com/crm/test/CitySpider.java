package com.crm.test;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.crm.utils.HttpUtils;
import com.crm.utils.baidu.LngAndLatUtil;

public class CitySpider extends Thread{
	public void run() {
		try{
			URI uri = getClass().getResource("/spider.xml").toURI();
			System.out.println(uri.toString());
			//新建文档
			Document doc = null;
			doc = DocumentHelper.createDocument();
			Element r = doc.addElement("Location");
			
			String text = HttpUtils.doGet("http://220.249.89.138/web-crm/static/city.xml", null, "utf-8", true);
			Document document = DocumentHelper.parseText(text);
			Element root = document.getRootElement();
			List<Element> list = root.selectNodes("/Location/CountryRegion");
			for(Element e:list){
				String country = e.attributeValue("Name");
				country = country == null ? "" : country;
				List<Element> l = e.selectNodes("State");
				for(Element ee:l){
					String state = ee.attributeValue("Name");
					state = state == null ? "" : state;
					List<Element> ll = ee.selectNodes("City");
					for(Element eee:ll){
						String city = eee.attributeValue("Name");
						city = city == null ? "" : city;
						String address = country+state+city;
						long start = System.currentTimeMillis();
						Map<String,String> map = LngAndLatUtil.getLngAndLat(address);
						long end = System.currentTimeMillis();
						if(map.get("lat").equals("")){
							break;
						}else{
							Element c = r.addElement("City");
							c.addAttribute("name", city);
							c.addAttribute("lat", map.get("lat"));
							c.addAttribute("lng",map.get("lng"));
							System.out.println(address+":"+map.get("lat")+","+map.get("lng")+","+(end-start)+"ms");
						}
					}
				}
			}
			//完成
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(new FileOutputStream(new File(uri)),format);
			writer.write(doc);
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws DocumentException {
		
		Thread t = new CitySpider();
		t.start();
		
		
	}
}
