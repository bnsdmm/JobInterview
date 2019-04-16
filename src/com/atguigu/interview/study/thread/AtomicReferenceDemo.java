package com.atguigu.interview.study.thread;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;
@ToString
@Setter
@AllArgsConstructor
class User{
    String userName;
    int age;
}
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3=new User("z3",12);
        User li4=new User("li4",35);
        AtomicReference<User> atomicReference=new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println((atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString()));
        System.out.println((atomicReference.compareAndSet(z3, li4) + "\t" + atomicReference.get().toString()));

    }
}
