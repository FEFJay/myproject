package com.login.biz;

import com.login.dao.UserDao;

public class UserBizImpl implements UserBiz {
	UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	
	@Override
	public boolean checkLogin(String name, String password) {
		return userDao.login(name, password);
	}

	@Override
	public void addUser(String name, String password) {
		userDao.addUser(name, password);
		
	}
	@Override
	public void delUser(int id) {
		userDao.delUser(id);
		throw new RuntimeException("异常通知里的error info");
	}

}
