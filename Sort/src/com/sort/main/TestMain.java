package com.sort.main;


import java.util.Random;

import com.sort.bucket.BucketSort;
import com.sort.bucket.RadixSort;
import com.sort.exchange.BubbleSort;
import com.sort.exchange.QuickSort;
import com.sort.insert.InsertSort;
import com.sort.insert.ShellSort;
import com.sort.merge.*;
import com.sort.select.HeapSort;
import com.sort.select.SelectSort;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random random = new Random();
		int len = 10;
		int[] a = null;
		int[] b = null;
		int[] c = null;
		int[] d = null;
		int[] e = null;
		int[] f = null;
		int[] g = null;
		int[] h = null;
		int[] k = null;


//		System.out.println("数据量\\算法	InsertSort \t ShellSort \t SelectSort \t HeapSort \t BubbleSort \t QuickSort \t MergeSort \t BucketSort \t RadixSort");
		System.out.println("数据量\\算法	ShellSort  \t HeapSort \t QuickSort \t MergeSort \t BucketSort \t RadixSort");
		System.out.print("--------------------------------------------------------------------------------------------------------------------------------------");
		
		for (int j = 1; j < 8; j++) {
			 a = new int[len];
			 b = new int[len];
			 c = new int[len];
			 d = new int[len];
			 e = new int[len];
			 f = new int[len];
			 g = new int[len];
			 h = new int[len];
			 k = new int[len];
			
			for (int i = 0; i < len; i++) {
				a[i] = b[i] = c[i] =d[i] = e[i] = f[i] = g[i] = h[i] = k[i] = random.nextInt(1000);
//				 System.out.println(a[i]+" "+ e[i]+ " " + k[i]);
			}	 
			
			System.out.print("\n"+len+"\t"); 
			
//			 run(new InsertSort(), a);
			 run(new ShellSort(), b);
//			 run(new SelectSort(), c);
			 run(new HeapSort(), d);
//			 run(new BubbleSort(), e);
			 run(new QuickSort(), f);
			 run(new MergeSort(), g);
			 run(new BucketSort(), h);
			 run(new RadixSort(), k);
			
			len *= 10;//1000 1万 10万 100万 1000万
		}
		
		
 
		


	}
	
	
	
	
	private static void show(int[] a) {
//		for (int i=0; i<a.length;i++) {
//			if (i == a.length-1) {
//				System.out.println(a[i]);
//			} else {
//				System.out.print(a[i]+",");
//			}
//		}
	}
	
	
	private static void run(MySort mySort, int[] a) {
		long t9 = System.currentTimeMillis();
		mySort.sort(a);
		
		long t10 = System.currentTimeMillis();
		System.out.print("	"+(t10 - t9)+"ms \t ");
        a=null;
	}
	
	
	
	private static void t() {
		Random random = new Random();
		int len = 10000;
		int[] a = null;
		int[] b = null;
		int[] c = null;
		int[] d = null;
		int[] e = null;

		 a = new int[len];
		 b = new int[len];
		 c = new int[len];
		 d = new int[len];
		 e = new int[len];
		 
		for (int i = 0; i < len; i++) {
			a[i] = b[i] = c[i] =d[i] = e[i] = random.nextInt(1000);

		}	
		
		System.out.println("--------------------------");
		long t1 = System.currentTimeMillis();
		new HeapSort().sort(b);
		long t2 = System.currentTimeMillis();
		System.out.println("耗时="+(t2 - t1)/1000.0+"s");
		show(b);
		
		System.out.println("--------------------------");
		long t3 = System.currentTimeMillis();
		new QuickSort().sort(c);
		long t4 = System.currentTimeMillis();
		System.out.println("耗时="+(t4 - t3)/1000.0+"s");
		show(c);
		
		System.out.println("--------------------------");
		long t7 = System.currentTimeMillis();
		new MergeSort().sort(d);
		long t8 = System.currentTimeMillis();
		System.out.println("耗时="+(t8 - t7)/1000.0+"s");
		show(d);
		
		System.out.println("--------------------------");
		long t5 = System.currentTimeMillis();
		new BubbleSort().sort(e);
		long t6 = System.currentTimeMillis();
		System.out.println("耗时="+(t6 - t5)/1000.0+"s");
		show(e);
		

		System.out.println("--------------------------");
		long t9 = System.currentTimeMillis();
		new InsertSort().sort(a);
		long t10 = System.currentTimeMillis();
		System.out.println("耗时="+(t10 - t9)/1000.0+"s");
		show(a);
		

	}
	

}
