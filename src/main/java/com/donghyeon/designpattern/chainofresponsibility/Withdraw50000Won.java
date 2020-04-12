package com.donghyeon.designpattern.chainofresponsibility;
//오만원
public class Withdraw50000Won implements WithdrawChain {
    //Withdraw10000Won 체인 연결
    private WithdrawChain withdrawChain;

    @Override
    public void setNextChain(WithdrawChain nextChain) {
        this.withdrawChain = nextChain;
    }
    @Override
    public void withdraw(Currency currency) {
        if(currency.getAmount() >= 50000) {
            int num = currency.getAmount()/50000;
            int remain = currency.getAmount() % 50000;
            System.out.println("5만원짜리 " +num+"장이 인출되었습니다.");
            if(remain != 0) this.withdrawChain.withdraw(new Currency(remain));
        } else {
            this.withdrawChain.withdraw(currency);
        }
    }
}
