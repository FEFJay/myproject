package com.multi.thread.cpu;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

 

public class Test {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		 int[] a = new int[100];
		 int temp = 0;
		 Random random = new Random();
		 
		 for (int i = 0; i < a.length; i++) {
			 a[i] = random.nextInt(20);
			 temp += a[i];
		}
		 System.out.println("the sum = "+temp);
		 
		 //构造可分解任务的线程池,//提交任务
		 ForkJoinPool pool = new ForkJoinPool();
		 Future<Integer> future = pool.submit(new CalTask(a, 0, a.length));
		 int result = future.get();
		 System.out.println("结果是="+result);
		 pool.shutdown();

	}
	
	
	
	static void call(String name){
		System.out.print("调用线程池.");

	 
		System.out.println("调用结束");
	}

}
