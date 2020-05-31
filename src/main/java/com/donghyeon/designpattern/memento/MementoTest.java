package com.donghyeon.designpattern.memento;

public class MementoTest {
    public static void main(String[] args) {

        FileWriterCaretaker caretaker = new FileWriterCaretaker();

        FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
        fileWriter.write("첫번 째 데이터!! ㅎ");
        System.out.println(fileWriter);

        System.out.println("-------------------");
        // 파일 저장
        caretaker.save(fileWriter);

        //다른 데이터 작성
        fileWriter.write("두번 째 데이터 !! ㅎㅎㅎ");


        //현재 파일의 데이터 출력
        System.out.println(fileWriter);

        //작성했던 데이터 되돌리기
        caretaker.undo(fileWriter);

        System.out.println("-----------undo()--------");
        //현재 파일의 데이터 출력
        System.out.println(fileWriter);

    }
}
