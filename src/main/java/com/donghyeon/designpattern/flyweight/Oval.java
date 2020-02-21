package com.donghyeon.designpattern.flyweight;
import java.awt.Color;
import java.awt.Graphics;

public class Oval implements Shape {

    //intrinsic 속성
    private boolean fill;

    public Oval(boolean f){
        this.fill=f;
        System.out.println("타원 만들기 , 색="+f);
        //딜레이 2초
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics circle, int x, int y, int width, int height,
                     Color color) {
        circle.setColor(color);
        circle.drawOval(x, y, width, height);
        if(fill){
            circle.fillOval(x, y, width, height);
        }
    }

}
