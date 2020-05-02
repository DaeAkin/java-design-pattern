package com.donghyeon.designpattern.state;

public class TVStartState implements State {
    @Override
    public void doAction() {
        System.out.println("TV 켜짐");
    }
}
