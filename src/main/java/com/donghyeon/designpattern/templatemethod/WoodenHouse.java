package com.donghyeon.designpattern.templatemethod;

public class WoodenHouse extends HouseTemplate {

    @Override
    public void buildWalls() {
        System.out.println("나무벽 만들기");
    }

    @Override
    public void buildPillars() {
        System.out.println("나무 기등 만들기");
    }

}
