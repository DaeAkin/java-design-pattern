package com.donghyeon.designpattern.observer;

public interface Subject {

    //옵저버를 등록하거나 등록해제하는 메소드
    void register(Observer observer);
    void unregister(Observer observer);

    //변경을 옵저버에게 알리는 메소드
    void notifyObservers();

    //subject로 부터 업데이트 내용을 가져오는 메소드
    Object getUpdate(Observer observer);

}
