package com.donghyeon.designpattern.decorator;

public class LuxuryCar extends CarDecorator {
    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("럭셔리 기능 추가!");
    }
}
