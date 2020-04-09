package com.donghyeon.designpattern.mediator;

public class MediatorTests {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Donghyeon");
        User user2 = new UserImpl(mediator, "SeoHyun");
        User user3 = new UserImpl(mediator, "Hodong");
        User user4 = new UserImpl(mediator, "Tae");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("안녕하세요!");
    }
}
