package com.donghyeon.designpattern.bridge;

public class GreenColor implements Color{
    @Override
    public void applyColor() {
        System.out.println("초록색");
    }
}
