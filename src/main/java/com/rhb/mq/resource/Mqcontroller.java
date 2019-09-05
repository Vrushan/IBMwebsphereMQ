package com.rhb.mq.resource;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import com.rhb.mq.services.MQconnection;

@RestController
public class Mqcontroller {

	@Autowired
	private MQconnection mqconnection;
	
	String getmqconnection()
	{
		XML xml=null;
		try {
			xml=new XMLDocument(new File("C:\\Xml doc"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mqconnection.getmqconnection(xml.toString(), "123456");
	}
	
}
