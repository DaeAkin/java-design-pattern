package com.donghyeon.designpattern.bridge;

public abstract class Shape {
    //구성
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract public void applyColor();
}
