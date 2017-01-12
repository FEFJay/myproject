package com.sunshine.pm2d5.test;

import com.sunshine.pm2d5.utils.Constant;

public class Test {

	public static void main(String[] args) throws Exception {

//		System.out.println(Constant.getCurrentTime());
		
		for (int i = 0; i < 5; i ++){
			Constant.log2File(i + "");
		}
	}

}
