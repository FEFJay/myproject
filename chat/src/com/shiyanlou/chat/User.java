package com.shiyanlou.chat;

import javax.websocket.Session;

public class User {
	private String nickName;
	private Session session;
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	private User(){
		
	}
	
	public User(String nickName,Session session){
		this.nickName = nickName;
		this.session = session;
	}
	
	

}
