package com.donghyeon.designpattern.state;

public class TVStopState implements State {
    @Override
    public void doAction(TVContext state) {
        state.setTvState(new TVStartState());
        System.out.println("TV가 꺼짐");
    }
}
