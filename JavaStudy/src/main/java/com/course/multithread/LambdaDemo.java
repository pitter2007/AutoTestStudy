package com.course.multithread;

public class LambdaDemo {
    public static void main(String[] args) {

        //匿名内部类实现多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程创建了");
            }
        }).start();


        //lambda方式实现多线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "线程创建了");
        }
        ).start();
    }
}
