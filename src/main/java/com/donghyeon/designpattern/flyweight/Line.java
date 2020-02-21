package com.donghyeon.designpattern.flyweight;

import java.awt.Color;
import java.awt.Graphics;

public class Line implements Shape {

    public Line(){
        System.out.println("선 만들기");
        // 딜레이 2초
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics line, int x1, int y1, int x2, int y2,
                     Color color) {
        line.setColor(color);
        line.drawLine(x1, y1, x2, y2);
    }

}
