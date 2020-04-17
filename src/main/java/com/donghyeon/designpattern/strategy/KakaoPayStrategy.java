package com.donghyeon.designpattern.strategy;

public class KakaoPayStrategy implements PaymentStrategy{

    private String kakaoEmail;
    private String kakaoPassword;

    public KakaoPayStrategy(String kakaoEmail, String kakaoPassword) {
        this.kakaoEmail = kakaoEmail;
        this.kakaoPassword = kakaoPassword;
    }

    @Override
    public void pay(int amount) {
        System.out.println("카카오페이로 " +amount+ "원 결제했습니다.");
    }
}
