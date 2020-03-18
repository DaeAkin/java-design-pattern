package com.donghyeon.designpattern.decorator.example;

//ConcreteDecorator
public class Shot extends CompoundDecorator {

    int shotCost = 500;
    public Shot(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost()+shotCost;
    }
}
