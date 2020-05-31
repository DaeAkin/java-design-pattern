package com.donghyeon.designpattern.memento;

public class FileWriterCaretaker {
    //Memento 클래스를 구성하고 있음.
    private Object object;

    public void save(FileWriterUtil fileWriterUtil) {
        this.object = fileWriterUtil.save();
    }

    public void undo(FileWriterUtil fileWriterUtil) {
        fileWriterUtil.undoToLastSave(object);
    }
}
