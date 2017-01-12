package com.multi.thread;
 

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadPool pool = ThreadPool.getInstance();
		for (int i = 1; i < 25; i++) {
			call("线程"+i);
			pool.show();
		}
		 // 关闭线程池
		ThreadPool.MY_POOL.shutdown();

	}
	
	
	
	static void call(String name){
		System.out.print("调用线程池.");
//		System.out.println(ThreadPool.threadPool.toString());
		ThreadPool.MY_POOL.execute(new MyThread(name));
		System.out.println("调用结束");
	}

}
