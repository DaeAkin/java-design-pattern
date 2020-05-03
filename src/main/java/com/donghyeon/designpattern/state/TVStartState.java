package com.donghyeon.designpattern.state;

public class TVStartState implements State {
    @Override
    public void doAction(TVContext state) {
        state.setTvState(new TVStopState());
        System.out.println("TV 켜짐");
    }
}
