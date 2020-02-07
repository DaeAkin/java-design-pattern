package com.donghyeon.designpattern.abstractfactory;

import com.donghyeon.designpattern.factory.Computer;

public class ComputerFactoryForAbstractFactory {
    public static Computer getComputer(ComputerAbstractFactory factory){
        return factory.createComputer();
    }
}
