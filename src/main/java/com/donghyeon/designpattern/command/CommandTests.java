package com.donghyeon.designpattern.command;

public class CommandTests {
    public static void main(String[] args) {
        //OS 따른 리시버 객체 생성
        FileSystemReceiver fs = FileSystemReceiverUtil.getUnderlyingFileSystem();

        //Command 객체를 만들고 리시버 연결
        OpenFileCommand openFileCommand = new OpenFileCommand(fs);

        //Invoker 객체를 만들고 Command 객체와 연결
        FileInvoker file = new FileInvoker(openFileCommand);

        file.execute();

        WriteFileCommand writeFileCommand = new WriteFileCommand(fs);
        file = new FileInvoker(writeFileCommand);
        file.execute();

        CloseFileCommand closeFileCommand = new CloseFileCommand(fs);
        file = new FileInvoker(closeFileCommand);
        file.execute();
    }
}
