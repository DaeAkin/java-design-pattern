package com.donghyeon.designpattern.builder;

public class TestBuilderPattern {

    public static void main(String[] args) {

        Computer com1 = new Computer.ComputerBuilder("500 GB", "2 GB")
                .setBluetoothEnabled(true)
                .setGraphicsCardEnabled(true).build();

        //블루투스랑 그래픽카드를 사용하지 않으면 이렇게 만든다.
        Computer com2 = new Computer.ComputerBuilder("1 TB" , "16 GB")
                .build();
    }
}
