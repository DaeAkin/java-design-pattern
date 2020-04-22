package com.donghyeon.designpattern.command;

public class CloseFileCommand implements Command{

    private FileSystemReceiver fileSystem;

    public CloseFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }
    @Override
    public void execute() {
        this.fileSystem.closeFile();
    }
}
