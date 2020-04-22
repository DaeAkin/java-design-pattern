package com.donghyeon.designpattern.command;

//리시버 클래스
public interface FileSystemReceiver {
    void openFile();
    void writeFile();
    void closeFile();
}
