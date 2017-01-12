package com.sunshine.pm2d5.beans;

import java.util.ArrayList;

//一个爬取的单元
public class CrawerBeans {

	//定义相关的变量
	public String city_name;
	public String[] data;
	public String start_time;
	
	//构造函数
	public CrawerBeans(String city_name, String[] data, String start_time) {
		this.city_name = city_name;
		this.data = data;
		this.start_time = start_time;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
}
