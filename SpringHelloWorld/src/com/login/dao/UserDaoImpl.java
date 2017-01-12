package com.login.dao;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean login(String name, String password) {
		String name0 = "kb";
		String pwd = "123";
		
		if (name0.equals(name) && pwd.equals(password)) {
			System.out.println("登录成功");
			return true;
		} else {
			System.out.println("登录失败");
			return false;
		}
	}

	@Override
	public void addUser(String name, String password) {
		System.out.println("addUser");
		
	}

	@Override
	public void delUser(int id) {
		System.out.println("delUser");
		
	}

}
