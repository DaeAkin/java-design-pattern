package com.donghyeon.designpattern.mediator;

//Mediator 뼈대 만들기
public interface ChatMediator {
    public void sendMessage(String msg,User user);
    void addUser(User user);
}
