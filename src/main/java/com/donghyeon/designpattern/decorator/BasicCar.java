package com.donghyeon.designpattern.decorator;

import com.donghyeon.designpattern.decorator.Car;

//컴포넌트 구현체
public class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.println("기본 기능 자동차");
    }
}
