package com.donghyeon.designpattern.templatemethod;

public abstract class HouseTemplate {

    //템플릿 메소드, 오버라이드 하면 안됨.!
    public final void buildHouse(){
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("집 완성!");
    }

    private void buildWindows() {
        System.out.println("유리창 만들기");
    }

    // 자식클래스가 구현해야함
    public abstract void buildWalls();
    public abstract void buildPillars();

    private void buildFoundation() {
        System.out.println("모래와 시멘트로 기반 다지기");
    }
}
