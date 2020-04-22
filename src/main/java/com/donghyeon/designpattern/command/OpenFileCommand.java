package com.donghyeon.designpattern.command;

public class OpenFileCommand implements Command {

    private FileSystemReceiver fileSystem;

    public OpenFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }
    @Override
    public void execute() {
        //openFileCommand 가  openFile() 메소드로 요청을 포워딩 해준다.
        this.fileSystem.openFile();
    }
}
