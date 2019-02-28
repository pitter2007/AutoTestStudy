package com.course.multithread;

public class ThreadDemo {
    public static void main(String[] args) {
        RunnnbleImpI run = new RunnnbleImpI();
        Thread t = new Thread(run);
        t.start();

        //匿名内部类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程创建了");
            }
        };
        new Thread(runnable).start();

        //再简化
        new Thread(new Runnable() {
            @Override
            public void run() {System.out.println(Thread.currentThread().getName() + "线程创建了");
            }
        }).start();
    }
}
