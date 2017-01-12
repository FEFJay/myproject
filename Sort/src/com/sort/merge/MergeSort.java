package com.sort.merge;

import com.sort.main.MySort;

/** 
 * 归并排序 
 * 简介:将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列 
 * 时间复杂度为O(nlogn) 
 * 稳定排序方式 
 */  
public class MergeSort implements MySort{

	public void sort(int[] a) {
//		System.out.println("MergeSort");
		 if (null == a || a.length < 2) {
			return;
		}
		 
		mergeSort(a,0,a.length-1);
	}
	

	private void mergeSort(int[] a, int left, int right) {
		int middle = (left + right) / 2;
		
		if (middle < left || middle+1 > right) {
			return;
		}
		
		mergeSort(a, left, middle);//递归式合并左边数组
		mergeSort(a, middle + 1, right);//递归式合并右边的数组
		
		merge(a, left, middle, right);//只有到最底层两个元素为一组的时候，这个地方才会第一次调用。然后不断回调结果给上一层递归调用。
	}





	private void merge(int[] a, int left, int middle, int right) {
		int len = right - left +1 ;
		int[] temp = new int[len];
		int l = left;
		int r = middle+1;
		int k = 0;

		while (l <= middle && r <= right) {
			temp[k++] = (a[l] <= a[r]) ? a[l++] : a[r++]; //赋值到新的数组之后，要把指指针往右滑动
		}//当循环结束之后，说明l == middle 或者 r == right至少有一个满足了。也就是以Middle为分割线，最多还有一边的数组的值没有拷贝到新数组。
		
		
		//处理可能剩下的值
		while (l <= middle) {
			temp[k++] = a[l++]; //赋值到新的数组之后，要把指指针往右滑动
		}
		while (r <= right) {
			temp[k++] = a[r++]; //赋值到新的数组之后，要把指指针往右滑动
		}
		
		//把排序后的数组还原到原数组
		for (int i = 0; i < len; i++) {
			a[left+i] = temp[i];
		}
		
	}





	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,66,88,0,-9};
		
		new MergeSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}
	}


}
