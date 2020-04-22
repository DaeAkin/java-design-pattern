package com.donghyeon.designpattern.command;

public class UnixFileSystemReceiver implements FileSystemReceiver {

    @Override
    public void openFile() {
        System.out.println("파일 열기 in unix OS");
    }

    @Override
    public void writeFile() {
        System.out.println("파일 쓰기 in unix OS");
    }

    @Override
    public void closeFile() {
        System.out.println("파일 닫기 in unix OS");
    }

}