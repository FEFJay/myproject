package com.sort.main;

public class T {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.out.println(Math.log10(10)/Math.log10(2));

//		int[] scores = new int[50];
//		for (int i = 200; i < 250; i++) {
//			 scores[i-200] = i;
//		}
		

		for (int i = -3; i < 3; i++) {
			System.out.println(",i="+i+":"+isOdd(i));
		}
		

		
		
		
	}
	
	
	
	static boolean isOdd(int i){
		System.out.print(i+"%2="+i%2);
		return i%2 ==1;
	}
	

}
