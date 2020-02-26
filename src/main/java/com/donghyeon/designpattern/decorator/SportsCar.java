package com.donghyeon.designpattern.decorator;

public class SportsCar extends CarDecorator {
    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("스포츠 기능 추가!");
    }
}
