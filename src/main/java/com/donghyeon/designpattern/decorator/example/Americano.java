package com.donghyeon.designpattern.decorator.example;

public class Americano extends Beverage {

    int price = 5000;

    @Override
    public int getCost() {
        return price;
    }
}
