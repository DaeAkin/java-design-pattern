package com.donghyeon.designpattern.state;

public class BeforeTvRemote {
    private String state="";

    public void setState(String state){
        this.state=state;
    }

    public void doAction(){
        if(state.equalsIgnoreCase("ON")){
            System.out.println("TV가 켜짐.");
        }else if(state.equalsIgnoreCase("OFF")){
            System.out.println("TV가 꺼짐");
        }
    }

    public static void main(String args[]){
        BeforeTvRemote remote = new BeforeTvRemote();

        remote.setState("ON");
        remote.doAction();

        remote.setState("OFF");
        remote.doAction();
    }
}
