# Command Pattern

커맨드 패턴은 요청이 Invoker에게 전달되어 Invoker가 캡슐화된 Command 오브젝트에게 전달해주는 패턴입니다.

Command 오브젝트는 어떤 특정한 동작을 수행하기 위해 요청을 적절한 Receiver 메소드에게 전달 해줍니다.

클라이언트는 receiver 객체를 만들고 Command를 붙이면 됩니다. 그런 다음 Invoker 객체를 만들고 동작을 수행할 Command 객체를 붙이면 됩니다.



![](https://upload.wikimedia.org/wikipedia/commons/8/8e/Command_Design_Pattern_Class_Diagram.png)



## 예제

파일 시스템에서 파일을 열기(open), 쓰기(write), 닫기(close) 하는 메소드를 만든다고 가정해 봅시다.

이 파일 시스템은 윈도우즈나 Unix 기반 운영체제를 지원해야 합니다. 이렇게 두 가지 운영체제를 지원해야 하는데, 운영체제마다 파일을 열고 쓰고 닫는 동작은 다르기 때문에, 이 두 가지 운영체제에 대한 구현을 따로 해줘야 합니다.

### 리시버 클래스

이 파일 시스템을 구현하기 위해서는 먼저 실제로 동작하는 리시버 클래스를 만들어야 합니다.
각기 다른 운영체제(윈도우, Unix등)에서 동작해야 하기 때문에 인터페이스로 선언 먼저 해주겠습니다.

**FileSystemReceiver.java**

```java
//리시버 클래스
public interface FileSystemReceiver {
    void openFile();
    void writeFile();
    void closeFile();
}
```

**UnixFileSystemReceiver.java**

```java
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
```

**WindowsFileSystemReceiver.java**

```java
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
```



## Command 클래스

Command 클래스를 만들기 위해선 추상클래스나 인터페이스를 사용합니다.

기본으로 구현해야할 동작은 없기 때문에 인터페이스를 이용하겠습니다.

**Command.java**

```java
public interface Command {
    void execute();
}
```

이제 리시버가 사용할 다른 타입들의 동작들을 구현해야 합니다. 3가지 동작이 있기 때문에, Command 인터페이스를 구현한 3가지 클래스가 나오게 됩니다.

**OpenFileCommand.java**

```java
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
```

**CloseFileCommand.java**

```java
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
```

**WriteFileCommand.java**

```java
public class WriteFileCommand implements Command {
    private FileSystemReceiver fileSystem;

    public WriteFileCommand(FileSystemReceiver fileSystem) {
        this.fileSystem = fileSystem;
    }
    @Override
    public void execute() {
        this.fileSystem.writeFile();
    }
}
```

이제 리시버와 커맨드 구현이 완료 되었습니다. 다음은 invoker 클래스를 구현해보겠습니다.



## Invoker 클래스

**FileInvoker.java**

```java
public class FileInvoker {

    private Command command;

    public FileInvoker(Command command) {
        this.command = command;
    }

    public void execute() {
        this.command.execute();
    }
}
```

Invoker는 Command 객체를 캡슐화하며 리퀘스트를 처리하기 위해 커맨드 객체에 리퀘스트를 전달합니다.

그 전에 사용자가 사용하고 있는 OS가 어떤건지 확인해주는 클래스를 팩토리 패턴을 사용해서 만들어 보겠습니다.

**FileSystemReceiverUtil.java**

```java
public class FileSystemReceiverUtil {
    public static FileSystemReceiver getUnderlyingFileSystem(){
        String osName = System.getProperty("os.name");
        System.out.println("사용중인 OS:"+osName);
        if(osName.contains("Windows")){
            return new WindowsFileSystemReceiver();
        }else{
            return new UnixFileSystemReceiver();
        }
    }
}
```



## 테스트

**CommandTests.java**

```java
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
```



**결과**

```java
사용중인 OS:Mac OS X
파일 열기 in unix OS
파일 쓰기 in unix OS
파일 닫기 in unix OS
```



## UML

 ![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/CommandPatternUML.png?raw=true)



## 정리

- Invoker 클래스는 클라이언트에서 커맨드 객체로 요청을 전달해주는 역할만 합니다.

- 클라이언트는 커맨드 객체와 리시버 객체를 만들어 적절하게 둘을 연결해주는 책임을 갖고 있습니다.

- 클라이언트는 또한 invoker 객체를 만들고 커맨드 객체와 연결시켜줘야 합니다.

- 커맨드 디자인 패터는 쉽게 확장가능하며, 클라이언트 코드 변경 없이 리시버안에 새로운 메소드를 추가할 수 있고, 새로운 커맨드 구현 클래스를 만들 수 있습니다.

  