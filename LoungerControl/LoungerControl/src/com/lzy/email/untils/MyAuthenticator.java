package com.lzy.email.untils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	String userName = null;
	String passWord = null;
	public MyAuthenticator() {
		// TODO Auto-generated constructor stub
	}
	
	public MyAuthenticator(String userName,String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, passWord);
	}
}
