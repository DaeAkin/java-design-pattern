package com.donghyeon.designpattern.abstractfactory;

import com.donghyeon.designpattern.factory.Computer;

public class TestAbstractFactoryPattern {

     public static void main(String[] args) {
        testAbstractFactory();
      }

    private static void testAbstractFactory() {
        Computer pc = ComputerFactoryForAbstractFactory.getComputer(new PCFactory("2 GB","500 GB","2.4 GHz"));
        Computer server = ComputerFactoryForAbstractFactory.getComputer(new ServerFactory("16 GB","1 TB","2.9 GHz"));
        System.out.println("AbstractFactory PC Config::"+pc);
        System.out.println("AbstractFactory Server Config::"+server);
    }
}
