package com.multi.thread;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
   private static BlockingQueue<Runnable> MY_QUEUE = new LinkedBlockingQueue<Runnable>();
   public static ThreadPoolExecutor MY_POOL = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS,MY_QUEUE, new ThreadPoolExecutor.AbortPolicy());
    
   /*饿汉式单例模式:创建对象*/
   private ThreadPool(){}
   private static class MyPool{
	   public static final ThreadPool POOL_INSTANCE = new ThreadPool();
   }
   public static final ThreadPool getInstance() {
	   return MyPool.POOL_INSTANCE;
   }
   
   
   
   public void show() {
   	   System.out.println("当前线程池大小[" + MY_POOL.getPoolSize() + "],当前队列大小[" + MY_QUEUE.size() + "]");
	}
   
    
   
   
   
//    public void run(){
//    	   // 创建线程池，为了更好的明白运行流程，增加了一些额外的代码
//        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
////      BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(2);
////      BlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
////      BlockingQueue<Runnable> queue = new SynchronousQueue<Runnable>();
//
//      
//      // AbortPolicy/CallerRunsPolicy/DiscardOldestPolicy/DiscardPolicy
//      //如果workQueue使用LinkedBlockingQueue队列，因为它是无界的，队列永远不会满，所以maximumPoolSize参数是没有意义的，同样keepAliveTime、unit、handler三个参数都无意义。
//      //参数：核心线程数目，最大线程数目，当前线程池线程总数大于核心线程数时终止多余的空闲线程的时间，上一个时间的单位，线程队列，拒绝策略（当线程池与workQueue队列都满了的情况下对新加任务采取的策略）
//      ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,queue, new ThreadPoolExecutor.AbortPolicy());
//
//      
////      // 向线程池里面扔任务
////      for (int i = 0; i < 500; i++) {
////          System.out.println("当前线程池大小[" + threadPool.getPoolSize() + "],当前队列大小[" + queue.size() + "]");
////
////          threadPool.execute(new MyThread("Thread" + i));//往线程池加入线程
////      }
//      
//      // 关闭线程池
//      threadPool.shutdown();
//    }
    
    

    
    
    
}













