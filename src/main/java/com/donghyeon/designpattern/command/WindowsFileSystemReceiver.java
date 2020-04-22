package com.donghyeon.designpattern.command;

public class WindowsFileSystemReceiver implements FileSystemReceiver {
    @Override
    public void openFile() {
        System.out.println("파일 열기 in 윈도우 OS");
    }

    @Override
    public void writeFile() {
        System.out.println("파일 쓰기 in 윈도우 OS");
    }

    @Override
    public void closeFile() {
        System.out.println("파일 닫기 in 윈도우 OS");
    }
}
