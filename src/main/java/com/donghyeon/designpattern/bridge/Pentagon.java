package com.donghyeon.designpattern.bridge;

public class Pentagon extends Shape {

    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.println("오각형이 색칠 됨.");
        color.applyColor();
    }
}
