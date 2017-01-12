package com.sort.exchange;

import com.sort.main.MySort;

public class BubbleSort implements MySort{


	public void sort(int[] a){
//		System.out.println("BubbleSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a.length-1-i; j++) {
				if (a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,66,88,0,-9};
		
		new BubbleSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}

	}
	
	
	

}
