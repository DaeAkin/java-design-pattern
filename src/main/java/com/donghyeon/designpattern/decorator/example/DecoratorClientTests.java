package com.donghyeon.designpattern.decorator.example;

public class DecoratorClientTests {
     public static void main(String[] args) {
          Beverage am = new Shot(new Shot((new Americano())));
          System.out.println("아메리카노 가격 : " + am.getCost());
      }
}
