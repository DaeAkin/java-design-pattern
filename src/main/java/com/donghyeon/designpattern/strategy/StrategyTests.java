package com.donghyeon.designpattern.strategy;

public class StrategyTests {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("쿼터파운드치즈버거",5800);
        Item item2 = new Item("콜라 R",1700);

        cart.addItem(item1);
        cart.addItem(item2);

        //카카오페이로 결제
        cart.pay(new KakaoPayStrategy("myemail@example.com", "mypwd"));

        //신용카드로 결제
        cart.pay(new CreditCardStrategy("Donghyeon Min", "1234567890123456", "786", "12/15"));
    }
}
