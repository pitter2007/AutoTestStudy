package com.course.multithread;

public class RunnnbleImpI implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程创建了");
    }
}
