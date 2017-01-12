package com.sort.exchange;

import com.sort.main.MySort;

public class QuickSort implements MySort{

	
	public void sort(int[] a){
//		System.out.println("QuickSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		quickSort(a, 0, a.length-1);
	}



	private void quickSort(int[] a,int left, int right) {
		int middle = partition(a, left, right);
		
		if (middle < left || middle > right) {
			return;
		}
		
		quickSort(a, left, middle-1);
		quickSort(a, middle+1, right);
	}



	private int partition(int[] a, int left, int right) {
		if (left < 0 || right > a.length-1 || left >= right) {
			return -1;
		}
		
		
        int temp = a[left];

        while (left < right) {
	        while (left < right && a[right] >= temp) {//比哨兵元素大的都在右边，一直往左边滑动
				right--;
			}
	        if (left < right) {
				a[left] = a[right];
				left++;
			}
	        
	        
	        while (left < right && a[left] <= temp) {//比哨兵元素小的都在左边，一直往右边滑动
				left++;
			}
	        if (left < right) {
				a[right] = a[left];
				right--;
			}
		}//while
        
        a[left] = temp;
		return left;
	}
	
	
	
    
	public static void main(String[] args) {
		int[] a = { 4, 5, 45, 6, 1, 7, 9, 66, 88, 0, -9 };

		new QuickSort().sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
	}

}
