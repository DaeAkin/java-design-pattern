package com.donghyeon.designpattern.state;

public class TVStopState implements State {
    @Override
    public void doAction() {
        System.out.println("TV가 꺼짐");
    }
}
