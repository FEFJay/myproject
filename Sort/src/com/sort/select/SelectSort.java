package com.sort.select;

import com.sort.main.MySort;

public class SelectSort implements MySort{


	public void sort(int[] a){
//		System.out.println("SelectSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		for (int i = 0; i < a.length-1; i++) {//i位置是待比较的值
			int min = i;
			for (int j = i+1; j < a.length ; j++) {//需要遍历剩下的所有元素，所以a[j] < a[min]不能放在循环控制条件里面，否则会出错。
				if (a[j] < a[min]) {
					min = j;
				}
				 
			}
			
			if (min != i) { //如果有找到比i位置的值小，则交换，否则不变
				int temp = a[i];
				a[i] = a[min];
				a[min] = temp;
			}
		}
	}
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,66,88,0,-9};
		
		new SelectSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}

	}
	
	
	

}
