package com.crm.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
	}
}
