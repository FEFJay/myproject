package com.sort.bucket;

import com.sort.main.MySort;


public class BucketSort implements MySort{
	
	public void sort(int[] a){
//		System.out.println("BucketSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		bucketSort(a);
		
	}
	
	
	/**************************************
	 * 
	 * @param 待排序数组
	 * @author JayLi
	 * @function 根据数组的最大值max，设定桶的个数（从0到max一共有max+1个），然后根据最低位优先法往桶里面放元素，再按照一定顺序倒出来即可.要求数组元素都是自然数。
	 * 
	 * 
	 * ***********************************/
	private void bucketSort(int[] a ) {
		//找到最大的元素
		int max = 0;
		for (int i : a) {
			if (i > max) {
				max = i;
			}
		}
		
		//计数数组，用于计算对应的桶一个有几个元素。注意要排序的元素，就是count数组的下标，这是一一对应的。
//		int len = max > a.length ? max : a.length;
		int len = max  ;
	    int[] count = new int[len+1];
		
		//开始计数，进行入桶操作
	    for (int i = 0; i < a.length; i++) {
			count[a[i]]++;
		}
		
		//进行出桶操作，完成出桶就完成排序
	    for (int i = 0, k = 0; i < count.length; i++) {
			for (; count[i] > 0; count[i]--) {
				a[k++] = i; //要排序的元素，就是count数组的下标
			}
		}
		
	}
	
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] a={4,5,45,6,1,7,9,166,88,0,4};
		int[] a={1,1,0,2};
		
		new BucketSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}

	}

}









