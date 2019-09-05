package com.rhb.mq.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class MqProperties {
	
	@Value("{ibm.mq.host}")
	private String host;
	
	@Value("{ibm.mq.port}")
	private Integer port;
	
	@Value("{ibm.mq.queue-manager}")
	private String queueManager;
	
	@Value("{ibm.mq.channel}")
	private String Channel;
	
	@Value("{ibm.mq.user}")
	private String username;
	
	@Value("{ibm.mq.password}")
	private String password;
	
	@Value("{ibm.mq.received-timeout}")
	private long receivedTimeout;
	
	@Value("{ibm.mq.ccsid}")
	private Integer ccsid;//character set Ascii code 819 standard
	
	@Value("{ibm.mq.recieve.queue}")
	private String recievequeue;
	
	@Value("{ibm.mq.send.queue}")
	private String sendqueue;

	//getters and setters
	
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getQueueManager() {
		return queueManager;
	}

	public void setQueueManager(String queueManager) {
		this.queueManager = queueManager;
	}

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String channel) {
		Channel = channel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getReceivedTimeout() {
		return receivedTimeout;
	}

	public void setReceivedTimeout(long receivedTimeout) {
		this.receivedTimeout = receivedTimeout;
	}

	public Integer getCcsid() {
		return ccsid;
	}

	public void setCcsid(Integer ccsid) {
		this.ccsid = ccsid;
	}

	public String getRecievequeue() {
		return recievequeue;
	}

	public void setRecievequeue(String recievequeue) {
		this.recievequeue = recievequeue;
	}

	public String getSendqueue() {
		return sendqueue;
	}

	public void setSendqueue(String sendqueue) {
		this.sendqueue = sendqueue;
	}

	//tostring method
	@Override
	public String toString() {
		return "MqProperties [host=" + host + ", port=" + port + ", queueManager=" + queueManager + ", Channel="
				+ Channel + ", username=" + username + ", password=" + password + ", receivedTimeout=" + receivedTimeout
				+ ", ccsid=" + ccsid + ", recievequeue=" + recievequeue + ", sendqueue=" + sendqueue + "]";
	}
	
	 


	
	
	
}
