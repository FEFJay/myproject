package com.multi.thread;

public class MyThread implements Runnable {
    private String name;
    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        // 做点事情
        try {
            Thread.sleep(1);
                
            System.out.println(name + "执行： finished job!") ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}