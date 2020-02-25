package com.donghyeon.designpattern.bridge;

public class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("빨간색");
    }
}
