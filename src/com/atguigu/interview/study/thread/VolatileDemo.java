package com.atguigu.interview.study.thread;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {
    volatile int number = 0;

    public void setNumber() {
        this.number = 10;
    }

    public void setNumberPlus() {
        this.number++;
    }
    AtomicInteger atomicInteger=new AtomicInteger();
    public void setMyAtomic(){
        atomicInteger.getAndIncrement();//相当于i++
    }}

public class VolatileDemo {
    public static void main(String[] args) {

        //seeOkByVolatile();
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.setNumberPlus();
                    myData.setMyAtomic();
                }
            }, String.valueOf(i)).start();
        }
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //默认两个线程 1main线程，2后台gc线程
        //等待多线程执行完下继续执行的最好办法
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomic finally number value: " + myData.atomicInteger);

    }


    //volatile可以保证可见性，及时通知其他线程，主物理内存的值被修改
    public static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.setNumber();
            System.out.println(Thread.currentThread().getName() + "\t updata number value: " + myData.number);
        }, "AAA").start();
        while (myData.number == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main get number value: " + myData.number);
    }

}
