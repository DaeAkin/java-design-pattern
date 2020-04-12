package com.donghyeon.designpattern.chainofresponsibility;
//만원
public class Withdraw10000Won implements WithdrawChain {

    private WithdrawChain withdrawChain;

    @Override
    public void setNextChain(WithdrawChain nextChain) {
        this.withdrawChain = nextChain;
    }
    @Override
    public void withdraw(Currency currency) {
        if(currency.getAmount() >= 10000) {
            int num = currency.getAmount()/10000;
            int remain = currency.getAmount() % 10000;
            System.out.println("1만원짜리 " +num+"장이 인출되었습니다.");
            if(remain != 0) this.withdrawChain.withdraw(new Currency(remain));
        } else {
            this.withdrawChain.withdraw(currency);
        }
    }
}
