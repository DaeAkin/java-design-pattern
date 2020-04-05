package com.donghyeon.designpattern.templatemethod;

public class GlassHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        System.out.println("유리벽 만들기");
    }
    @Override
    public void buildPillars() {
        System.out.println("유리 기둥 만들기");
    }

}