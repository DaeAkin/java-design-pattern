package com.donghyeon.designpattern.observer;

public interface Observer {

    //옵저버를 업데이트하는 메소드, subject가 사용함.
    void update();

    //옵저버에 subject 등록
    void setSubject(Subject sub);
}
