package com.multi.thread.cpu;

import java.util.concurrent.RecursiveTask;

/*将任务分解  然后利用多cpu  进行累加求和*/
public class CalTask extends RecursiveTask<Integer>{
	 private static final int T = 20;
	 
    private int[] arr;
    private int start;
    private int end;
    
    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.end = end;
        this.start = start;
    }

 

	@Override
	protected Integer compute() {
		 int sum = 0; 
		if(end - start < T){
			for (int i = start; i < end; i++) {
				sum += i;
			} 
			return sum;
		 }else {
			int middle = (start + end) / 2;
			CalTask left = new CalTask(arr, start, middle);
			CalTask right = new CalTask(arr, middle,end);
			left.fork();
			right.fork();
			
			
		    return left.join() + right.join();
		}
		
	}
}