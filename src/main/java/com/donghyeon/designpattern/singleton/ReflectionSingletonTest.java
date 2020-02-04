package com.donghyeon.designpattern.singleton;

import java.lang.reflect.Constructor;

public class ReflectionSingletonTest {

    public static void main(String[] args) {
        EagerInitializationSingleton instanceOne = EagerInitializationSingleton.getInstance();
        EagerInitializationSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializationSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializationSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("instanceOne : " + instanceOne.hashCode());
        System.out.println("instanceTWO : " +instanceTwo.hashCode());
    }

}
