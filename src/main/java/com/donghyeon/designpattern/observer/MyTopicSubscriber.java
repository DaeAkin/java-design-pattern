package com.donghyeon.designpattern.observer;

public class MyTopicSubscriber implements Observer {

    private String name;
    private Subject topic;

    public MyTopicSubscriber(String name){
        this.name=name;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: 새로운 메세지가 없습니다.");
        }else
            System.out.println(name+":: 메세지 수신 ::"+msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }
}
