package com.donghyeon.designpattern.memento;
//Originator 클래스
public class FileWriterUtil {

    private String fileName;
    private StringBuilder content;

    public FileWriterUtil(String fileName) {
        this.fileName = fileName;
        this.content = new StringBuilder();
    }

    @Override
    public String toString() {
        return this.content.toString();
    }

    public void write(String str) {
        this.content.append(str);
    }

    public Memento save() {
        return new Memento(this.fileName,this.content);
    }

    public void undoToLastSave(Object obj) {
        Memento memento = (Memento) obj;
        this.fileName = memento.fileName;
        this.content = memento.content;
    }
    //Memento 클래스
    private class Memento {
        private String fileName;
        private StringBuilder content;

        public Memento(String fileName, StringBuilder content) {
            this.fileName = fileName;
            //deep copy를 사용해서 FileWriterUtil의 content 변수와 동일한 레퍼런스를 갖지 않도록 함.
            this.content = new StringBuilder(content);
        }
    }
}
