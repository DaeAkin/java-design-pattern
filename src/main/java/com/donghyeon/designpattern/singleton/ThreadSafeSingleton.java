package com.donghyeon.designpattern.singleton;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }
    public static synchronized ThreadSafeSingleton getInstance() {
        if(instance == null) {
            return new ThreadSafeSingleton();
        }
        return instance;
    }
}
