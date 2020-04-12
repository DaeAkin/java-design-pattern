package com.donghyeon.designpattern.chainofresponsibility;

import java.util.Scanner;

public class ChainOfResponsibilityTests {
     public static void main(String[] args) {

         WithdrawChain withdraw100000Won = new Withdraw100000Won();
         WithdrawChain withdraw50000Won = new Withdraw50000Won();
         WithdrawChain withdraw10000Won = new Withdraw10000Won();

         withdraw100000Won.setNextChain(withdraw50000Won);
         withdraw50000Won.setNextChain(withdraw10000Won);

         while(true) {
             System.out.println("인출 할 돈을 눌러주세요");
             Scanner scanner = new Scanner(System.in);
             int money = scanner.nextInt();
             Currency currency = new Currency(money);
             withdraw100000Won.withdraw(currency);
             System.out.println("--------------");
         }

      }
}
