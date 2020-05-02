package com.donghyeon.designpattern.state;

public class TVContext implements State {

    private State tvState;

    public void setTvState(State tvState) {
        this.tvState = tvState;
    }

    public State getTvState() {
        return tvState;
    }

    @Override
    public void doAction() {

    }
}
