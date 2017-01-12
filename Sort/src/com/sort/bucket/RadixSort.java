package com.sort.bucket;

import java.util.ArrayList;
import java.util.List;

import com.sort.main.MySort;

/*
 * 基数排序
 * */
public class RadixSort implements MySort{
	
	public void sort(int[] a){
//		System.out.println("RadixSort");
		if (null == a || a.length < 2) {
			return;
		}
		
		radixSort(a);
		
	}
	
	
	/**************************************
	 * 
	 * @param 待排序数组
	 * @author JayLi
	 * @function 根据数组的最大值max，设定桶的个数（从0到max一共有max+1个），
	 * 然后根据最低位优先法往桶里面放元素，再按照一定顺序倒出来即可.要求数组元素都是自然数。
	 * 
	 * ***********************************/
	private void radixSort(int[] a ) {
		
		//找到数组中的元素最大的位数（个1，十2、百3、千4、万5.。。）
		int time = 1;//time就表示最大的位数
		for (int i : a) {
			int len = String.valueOf(i).length();
			if (len > time) {
				time = len;
			}
		}
//		System.out.println(time);
		
		//使用list来构造“桶”，可以增加灵活性，使得长度可变长。如果使用数组，效率较差。
		List<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();//这样设计，就相当于是一个二维数组了
		for (int i = 0; i < 10; i++) { //基数排序的基数就是0-9，十个桶(10个ArrayList<Integer>()对象)，这里初始化
			queue.add(new ArrayList<Integer>());
		}
 
		//对目标数组的元素进行分组，进行入桶和出桶操作
	    for (int x = 0; x < time; x++) { //根据元素的最大位数，控制分组的次数（第一次是个位，第二次是十位，...）
			
	    	for (int i = 0; i < a.length; i++) {
				int index = a[i] % (int)Math.pow(10, x+1) / (int)Math.pow(10, x);//获取目标数组的元素对应位的数字。如a[i]=154，第一次分组x=0则个位index=154%10/1=4，第二次分组x=1则十位index=154%100/10=5，...
				ArrayList<Integer> queue2 = queue.get(index);//进行分组操作（所谓的“入桶”）
//				System.out.println("index="+index);
			    queue2.add(a[i]);
			    queue.set(index, queue2);
			}
			
			//进行出桶操作，完成出桶就完成排序
		    int k = 0;//这个用来表示目标数组的下标 ,元素计数器
		    for (int i = 0; i < 10; i++) {
		    	ArrayList<Integer> queue2 = queue.get(i);
		    	
//		    	for (int j = 0; j < queue2.size(); j++) {
//		    		a[k++] = queue2.get(j); //出桶的值赋值给原数组，并且指针在赋值结束之后要往后移动
//				}
//		    	queue2.clear();//本轮分组结束后需要清空，然后进行下一轮分组
		    	
		    	while (queue2.size() > 0) {
		    		a[k++] = queue2.get(0); //出桶的值赋值给原数组，并且指针在赋值结束之后要往后移动
		    		queue2.remove(0);
				}
			}
		    
			
		}//控制分组次数for
		

		
	}
	
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a={4,5,45,6,1,7,9,166,88,0,4};
		
		new RadixSort().sort(a);
		for (int i : a) {
			System.out.print(i+" ");
		}

	}

}









