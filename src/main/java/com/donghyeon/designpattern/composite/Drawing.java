package com.donghyeon.designpattern.composite;


import java.util.ArrayList;
import java.util.List;

public class Drawing implements Shape{

    //Shape의 컬렉션
    private List<Shape> shapes = new ArrayList<Shape>();

    @Override
    public void draw(String fillColor) {
        for(Shape sh : shapes)
        {
            sh.draw(fillColor);
        }
    }

    //색칠 할 도형 추가
    public void add(Shape s){
        this.shapes.add(s);
    }

    //색칠 할 도형 삭제
    public void remove(Shape s){
        shapes.remove(s);
    }

    //색칠 할 도형 전부 삭제
    public void clear(){
        System.out.println("Clearing all the shapes from drawing");
        this.shapes.clear();
    }
}

