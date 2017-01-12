package com.sort.insert;

import com.sort.main.MySort;

public class ShellSort implements MySort{


	public void sort(int[] a ){
//		System.out.println("ShellSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		double len = a.length;
		while (true) {
			len = Math.ceil(len / 2); //确定希尔排序的步长,向上取整数。注意这是double类型的
			int d = (int) len; //转为int类型
			
			for (int x = 0; x < d; x++) {//x是数组的下标,d是步长。x < d是有考究的，如果x=d，则下层循环i=2d，有可能直接导致数组越界。
				
				for (int i = x+d; i < a.length; i += d) { //数组下标间隔为d的分为一个组（i,i+d,i+2d,...）
					int j = i - d;
					int temp = a[i];
					for ( ; j >= 0 && a[j] > temp; j -= d) { //指正往左边滑动，且比较当前指针所在位置j的值和待比较的值temp，如果j位置的值更大，则该位置要后移。
						 a[j+d] = a[j];  //把比temp大的值往后移动一个长度，覆盖原有值
					}//循环结束，说明j位置的值不大于待比较值，不用往前插。此时j+step位置（因为循环结束之前来了一次j -= step操作）应该就是待插入元素的位置
					
					a[j+d] = temp;
				}//插入排序结束
			}//步长结束 for
			
			
			if (d == 1) {//步长为1的时候，已经是最后一次排序，且程序到这里说明已经排序结束，退出循环。
				break;
			}
		}

	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,66,88,0,-9};
		
		new ShellSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}

	}
	
	
	

}
