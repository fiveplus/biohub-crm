package com.crm.test;

import com.crm.utils.HttpUtils;
import com.crm.utils.aliyun.LngAndLatUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;


public class CitySpider extends Thread{
	public void run() {
		try{
			int number = 0;
			URI uri = getClass().getResource("/spider.xml").toURI();
			System.out.println(uri.toString());
			//新建文档
			Document doc = null;
			doc = DocumentHelper.createDocument();
			Element r = doc.addElement("Location");
			String text = HttpUtils.doGet("https://i.bridgebiomed.com/crm/static/city.xml", null, "utf-8", true);
			Document document = DocumentHelper.parseText(text);
			Element root = document.getRootElement();
			List<Element> list = root.selectNodes("/Location/CountryRegion");
			for(Element e:list){
				String country = e.attributeValue("Name");
				country = country == null ? "" : country;
				List<Element> l = e.selectNodes("State");
				for(Element ee:l){
					try{
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
								//Thread.sleep(500);
								number++;
								if(number >= 10){
									//完成
									OutputFormat format = OutputFormat.createPrettyPrint();
									XMLWriter writer = new XMLWriter(new FileOutputStream(new File(uri)),format);
									writer.write(doc);
									writer.close();
									return;
								}
							}
						}
					}catch(Exception ex){
						ex.printStackTrace();
						continue;
					}
					
				}
			}
			// 完成
			// OutputFormat format = OutputFormat.createPrettyPrint();
			// XMLWriter writer = new XMLWriter(new FileOutputStream(new File(uri)),format);
			// writer.write(doc);
			// writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws DocumentException {
		
		Thread t = new CitySpider();
		t.start();
		
		
	}
}
