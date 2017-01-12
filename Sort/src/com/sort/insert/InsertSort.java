package com.sort.insert;

import com.sort.main.MySort;

public class InsertSort implements MySort{


	public void sort(int[] a){
//		System.out.println("InsertSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		for (int i = 1; i < a.length; i++) {
			int j = i - 1;
			int temp = a[i];
			for ( ; j >= 0 && a[j] > temp; j--) { //指正往左边滑动，且比较当前指针所在位置j的值和待比较的值temp，如果j位置的值更大，则该位置要后移。
				 a[j+1] = a[j];  //把比temp大的值往后移动一个长度，覆盖原有值
			}//循环结束，说明j位置的值不大于待比较值，不用往前插。此时j+1位置（因为循环结束之前来了一次j--操作）应该就是待插入元素的位置
			
			a[j+1] = temp;
		}//插入排序结束
		
	}
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,66,88,0,-9};
		
		new InsertSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}

	}
	
	
	

}
