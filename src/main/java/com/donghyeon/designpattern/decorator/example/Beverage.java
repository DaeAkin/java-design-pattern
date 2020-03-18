package com.donghyeon.designpattern.decorator.example;

//Component 면서 ConcreteComponent
public abstract class Beverage {

    String description = "no desc";

    abstract int getCost();

    public String getDescription() {
        return this.description;
    }

}
