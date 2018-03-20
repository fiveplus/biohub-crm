package com.crm.test;

import com.crm.utils.HttpUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Test {
	
	void a() throws URISyntaxException, DocumentException{
		URI uri = getClass().getResource("/spider.xml").toURI();
		File file = new File(uri);
		Document document = new SAXReader().read(file);
		Element root = document.getRootElement();
		List<Element> list = root.selectNodes("/Location/City");
		for(Element e:list){
			String name = e.attributeValue("name");
			System.out.println(name);
		}
	}
	
	public static void main(String[] args) throws URISyntaxException, DocumentException {
		//new Test().a();
		String url = "http://192.168.1.113:8081/solr/";
		String res = HttpUtils.doGet(url, null, "utf8", true);
		System.out.println(res);
	}
}
