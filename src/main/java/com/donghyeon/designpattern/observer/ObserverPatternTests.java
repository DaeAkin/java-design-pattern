package com.donghyeon.designpattern.observer;

public class ObserverPatternTests {
    public static void main(String[] args) {
        //subject 생성
        MyTopic topic = new MyTopic();

        //옵저버들 생성
        Observer obj1 = new MyTopicSubscriber("Obj1");
        Observer obj2 = new MyTopicSubscriber("Obj2");
        Observer obj3 = new MyTopicSubscriber("Obj3");

        //subject에 옵저버들 등록
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        //옵저버에도 subject 등록
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        //업데이트 내용이 있는지 확인
        obj1.update();

        //subject에게 메세지 보내기
        topic.postMessage("안녕하세용~");
    }
}
