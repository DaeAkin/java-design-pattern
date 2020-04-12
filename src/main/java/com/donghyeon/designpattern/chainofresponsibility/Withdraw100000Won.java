package com.donghyeon.designpattern.chainofresponsibility;
//십만원
public class Withdraw100000Won implements WithdrawChain {
    //Withdraw50000Won 체인 연결
    private WithdrawChain withdrawChain;

    @Override
    public void setNextChain(WithdrawChain nextChain) {
        this.withdrawChain = nextChain;
    }
    @Override
    public void withdraw(Currency currency) {
        if(currency.getAmount() >= 100000) {
            int num = currency.getAmount()/100000;
            int remain = currency.getAmount() % 100000;
            System.out.println("10만원짜리 " +num+"장이 인출되었습니다.");
            if(remain != 0) this.withdrawChain.withdraw(new Currency(remain));
        } else {
            this.withdrawChain.withdraw(currency);
        }
    }
}
