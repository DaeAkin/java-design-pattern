package com.donghyeon.designpattern.chainofresponsibility;

public interface WithdrawChain {
    void setNextChain(WithdrawChain withdrawChain);
    void withdraw(Currency currency);
}
