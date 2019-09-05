package com.rhb.mq.services;

import javax.jms.JMSException;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.disthub2.impl.formats.OldEnvelop.payload.normal.body.jms.JMSCorrelationID;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.rhb.mq.component.MqProperties;

@Component
public  class MQconnection  {

	
	@Autowired
	MqProperties ibmproperties;
	
	public String getmqconnection(String Xmlformat,String JMSCorrelationID)
	//public MQQueueConnectionFactory mqqueueconnectionfactory(String Xmlformat,String JMSCorrelationID)
	{
		long timeout=5000;
		String consumemessage=null;
		CamelContext context= new DefaultCamelContext();//create camel context for routing i,e container
		
		MQQueueConnectionFactory mqqueueconnectionfactory = new MQQueueConnectionFactory();
		
		
		mqqueueconnectionfactory.setHostName(ibmproperties.getHost());
		try {
			mqqueueconnectionfactory.setChannel(ibmproperties.getChannel());
			
			mqqueueconnectionfactory.setTransportType(WMQConstants.WMQ_CLIENT_NONJMS_MQ);
			
			mqqueueconnectionfactory.setBooleanProperty(WMQConstants.WMQ_MQMD_WRITE_ENABLED, true);
			
			mqqueueconnectionfactory.setIntProperty(WMQConstants.WMQ_MQMD_MESSAGE_CONTEXT,WMQConstants.WMQ_MDCTX_SET_ALL_CONTEXT);
			
			mqqueueconnectionfactory.setCCSID(ibmproperties.getCcsid());
			
			mqqueueconnectionfactory.setPort(ibmproperties.getPort());
			
			mqqueueconnectionfactory.createConnection();
			
			System.out.println("The tostring of method is"+ ibmproperties.toString());
			
			System.out.println("The connection has been established successfully..!");
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("The connection with ibm failed.."+ e);
		}
		
		
		JmsComponent component= new JmsComponent(context);
		component.setConnectionFactory(mqqueueconnectionfactory);
		//component.setDestinationResolver(destinationResolver);
		
		context.addComponent("jms", component);//Add components indicates jms provider 
		
		//optional based on requirment
		
		if(JMSCorrelationID!=null)
		{
			
	 try {
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				
				from("direct:start").process(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						
						String message =exchange.getIn().getBody(String.class);
						
						exchange.getOut().setHeader("", JMSCorrelationID);
						
						exchange.getOut().setBody(message);
						
					}
				}).to("");
				
				from("").to("seda.end");
				
			}
		});
	} 
       catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		System.out.println("Exception occured"+ e );
	}
       
       context.start();
       
       context.createProducerTemplate().sendBody("direct:start",Xmlformat);
       System.out.println("The message is sending to ibm mq"+ibmproperties.getSendqueue());
       
       ConsumerTemplate consumertemplate = context.createConsumerTemplate();
       
        consumemessage =consumertemplate.receiveBody("seda:end", timeout,String.class);
       
       System.out.println("Response from queue"+ ibmproperties.getRecievequeue());
       return consumemessage;
	}
		else
		{
			System.out.println("Something error in formatt XMl..");
		}
          
		return  consumemessage;
		//return mqqueueconnectionfactory();
	}

	
	
}
