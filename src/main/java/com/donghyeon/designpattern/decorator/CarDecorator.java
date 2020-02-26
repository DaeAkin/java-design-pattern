package com.donghyeon.designpattern.decorator;

//데코레이터
public class CarDecorator implements Car {

    //자식 클래스에서 접근 가능
    protected Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        this.car.assemble();
    }
}
