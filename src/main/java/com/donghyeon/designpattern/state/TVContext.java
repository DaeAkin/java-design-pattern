package com.donghyeon.designpattern.state;

public class TVContext {

    private State tvState;

    public void setTvState(State tvState) {
        this.tvState = tvState;
    }

    public void doNext(TVContext state) {
        this.tvState.doAction(state);
    }
}
