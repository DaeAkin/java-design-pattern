package com.donghyeon.designpattern.state;

public class StatePatternTests {

    public static void main(String[] args) {
        TVContext context = new TVContext();
        State tvStartState = new TVStartState();
        context.setTvState(tvStartState);

        context.doNext(context);
        context.doNext(context);
    }
}
