package com.sort.select;

import com.sort.main.MySort;

public class HeapSort implements MySort{
	
	
	/***************************************
	 * 把一个数组使用堆排序，从小到大
	 * 
	 * @param 数组
	 * 
	 * ****************************************/
	public void sort(int[] a) {
//		System.out.println("HeapSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		initHeap(a);//初始化为大根堆，数组的第一个元素（大根堆的根）就是最大的元素了
		
		 //执行堆排序
		int len = a.length;
        int temp;
        for (int i = len-1; i >=1 ; i--) {//i是一轮排序后数组的长度
		    temp = a[0];//把最大的元素放在最后,待排序数组长度变小1
			a[0] = a[i];
			a[i] = temp;
			
			maxHeap(a, i, 0);//去掉最大元素后，堆结构变化，重新建立大根堆//已经大部分是大根堆，只是改了根节点
		}
	}
	

	
	
	/***************************************
	 * 把一个数组初始化为大根堆
	 * 
	 * @param 数组
	 * 
	 * ****************************************/
	private void initHeap(int[] a){
		if (null == a || a.length < 2) {
			return;
		}
		
		int len = a.length;
		for (int i = (len-1)/2; i >= 0; i--) {//下标是(len-1)/2的节点，是数组里面有child节点的最后一个。所以从它开始
			maxHeap(a, len, i);
		}
	}
	

	
	/***************************************************
	 * 
	 * 根据给定的根节点构造大根堆
	 * @param 待建堆数组名
	 * @param 待建堆数组的长度
	 * @param 根节点的数组下标
	 * ****************************************************/
	private void maxHeap(int[] a, int len, int index) {
		int left = index * 2 + 1;
		int right = index * 2 +2 ;
		int largest = index;
		
		if (left < len && a[left] > a[index]) {
			largest = left;
		}
		if (right < len && a[right] > a[largest]) {
			largest = right;
		}
		
		if (largest != index) {
			int temp = a[index];
			a[index] = a[largest];
			a[largest] = temp;
			
			maxHeap(a, len, largest);
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,66,88,0,-9};
		
		new HeapSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//             int[] a={4,5,45,6,1,7,9,66,88,0,-9};
//             
//             //建立大根堆
//             HeapSort test = new HeapSort();
//             int len = a.length;
//             for (int i = (len-1)/2; i >=0 ; i--) {
//				test.maxHeap(a, len, i);//初次构造大根堆
//			  }
//             
//             
//             //执行堆排序
//             int temp;
//             for (int i = len-1; i >=1 ; i--) {//i是一轮排序后数组的长度
//     		    temp = a[0];//把最大的元素放在最后,待排序数组变小
//    			a[0] = a[i];
//    			a[i] = temp;
//    			
//    			//去掉最大元素后，堆结构变化，重新建立大根堆
//    			test.maxHeap(a, i, 0);//已经大部分是大根堆，只是改了根节点
//    			
//			}
//		
//            for (int i : a) {
//				System.out.print(i+" ");
//			}
//             
//	}
	
	

}




















