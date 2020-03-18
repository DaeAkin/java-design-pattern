package com.donghyeon.designpattern.decorator.example;
//Decorator
public class CompoundDecorator extends Beverage {

    protected Beverage beverage;

    public CompoundDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public int getCost() {
        return this.beverage.getCost();
    }
}
