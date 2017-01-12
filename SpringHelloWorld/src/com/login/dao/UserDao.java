package com.login.dao;

public interface UserDao {
   public boolean login(String name,String password);
   public void addUser(String name,String password);
   public void delUser(int id);
}
