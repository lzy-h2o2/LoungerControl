package com.lzy.email.untils;

import java.util.Properties;

/**
 * 邮件信息 
 * */
public class MailSenderInfo {

	//发送邮件的服务器IP和端口
	private String mailServerHost;
	private String mailServerPort;
	//邮件发送者&接收者地址
	private String fromAddress;
	private String toAddresss;
	
	//登陆邮件发送服务器的用户名&密码
	private String userName;
	private String passWord;
	
	//是否需要身份验证
	private boolean validate = false;
	
	//邮件主题
	private String subject;
	//邮件文本内容
	private String content;
	//邮件附件文件名
	private String [] attachFileName;
	
	/**
	 * 
	 * 获得邮件会话属性
	 * 
	 * */
	public Properties getProperties(){
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", this.mailServerHost);
		properties.put("mail.smtp.port", this.mailServerPort);
		properties.put("mail.smtp.auth", validate ? "true":"false");
		
		return properties;
	}

	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddresss() {
		return toAddresss;
	}

	public void setToAddresss(String toAddresss) {
		this.toAddresss = toAddresss;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(String[] attachFileName) {
		this.attachFileName = attachFileName;
	}
	
	
}
