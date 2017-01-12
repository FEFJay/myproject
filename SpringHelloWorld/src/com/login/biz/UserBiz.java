package com.login.biz;

public interface UserBiz {
	   public boolean checkLogin(String name,String password);
	   public void addUser(String name,String password);
	   public void delUser(int id);
	}