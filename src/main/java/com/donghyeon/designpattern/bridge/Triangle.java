package com.donghyeon.designpattern.bridge;

public class Triangle extends Shape {

    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.print("삼각형이 색칠 됨.");
        color.applyColor();
    }
}
