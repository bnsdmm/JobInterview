package com.atguigu.interview.study.thread;

public class SingletonDemo {
    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println("This is singleton constructor");
    }

    public static SingletonDemo getSingletonDemo() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class){
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SingletonDemo.getSingletonDemo();
            }, String.valueOf(i)).start();
        }
    }
}
