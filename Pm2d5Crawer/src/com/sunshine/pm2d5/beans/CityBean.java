package com.sunshine.pm2d5.beans;

//一个城市的单元
public class CityBean {

	public String erea_city;
	public float pm2d5;
	public String pm2d5_date;
	
	//构造函数
	public CityBean(String erea_city, float pm2d5, String pm2d5_date) {
		this.erea_city = erea_city;
		this.pm2d5 = pm2d5;
		this.pm2d5_date = pm2d5_date;
	}
	
	public String getErea_city() {
		return erea_city;
	}
	public void setErea_city(String erea_city) {
		this.erea_city = erea_city;
	}
	public float getPm2d5() {
		return pm2d5;
	}
	public void setPm2d5(float pm2d5) {
		this.pm2d5 = pm2d5;
	}
	public String getPm2d5_date() {
		return pm2d5_date;
	}
	public void setPm2d5_date(String pm2d5_date) {
		this.pm2d5_date = pm2d5_date;
	}
	
}
