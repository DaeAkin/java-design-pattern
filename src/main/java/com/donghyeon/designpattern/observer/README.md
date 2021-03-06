# Observer(옵저버) 패턴 

옵저버 패턴은 동작 디자인 패턴 중 하나 입니다. **객체의 상태의 변화가 생기면 어떤 동작을 수행하고 싶을 때 유용합니다**. 옵저버 패턴

## 목적

**일 대 다 의존 관게예서 일(One)쪽의 객체의 상태가 변경이 되면, 다 쪽 객체들이 알게 되고, 자동적으로 업데이트 됩니다.**

**Subject는 옵저버리스트를 가지고 있어**, 객체의 상태의 변화가 있으면 알려줍니다. 그래서 Subject가 옵저버를 등록하거나 등록을 취소할 수 있는 메소드를 제공해줘야 합니다. Subject는 변화가 일어나면 모든 옵저버에게 통지하는 메소드가 있으며, 옵저버에게 통지되고 있는동안 업데이트를 보내주거나, 업데이트를 가져오기 위한 메소드를 제공해줄 수 있습니다. 

<u>옵저버는</u> **감시할 객체를 설정하기 위한 메소드와 객체의 변화가 생겼을 때 Subject가 호출할 메소드를**  갖고 있어야 합니다.

Java Message Service(JMS)는 Mediator 패턴과 함께 옵저버 패턴을 같이 사용함으로써 애플리케이션이 다른 애플리케이션으로 데이터를 보내거나 받을 수 있습니다.

MVC 프레임워크 또한 옵저버 패턴을 사용하는데, Model이 Subject이고 View가 옵저버 입니다.



## 옵저버 패턴 자바 예제

간단하게 토픽과 토픽을 등록하는 옵저버를 만들어 보겠습니다. 새로운 메세지가 토픽에 등록되면 등록된 모든 옵저버는 그 사실을 알아차리고, 메세지를 읽습니다.

Subject의 기본 요구 사항에 맞게 인터페이스를 설계해보겠습니다.

**Subject.java**

```java
public interface Subject {
    //옵저버를 등록하거나 등록해제하는 메소드
    void register(Observer observer);
    void unregister(Observer observer);

    //변경을 옵저버에게 알리는 메소드
    void notifyObservers();

    //subject로 부터 업데이트 내용을 가져오는 메소드
    Object getUpdate(Observer observer);
}
```

그 다음 Observer가 사용할 메소드를 선언해야합니다. subject를 등록할 *setSubject()*와 subject에서 옵저버를 호출할 *update()* 메소드가 있습니다.

**Observer.java**

```java
public interface Observer {

    //옵저버를 업데이트하는 메소드, subject가 사용함.
    void update();

    //옵저버에 subject 등록
    void setSubject(Subject sub);
}
```

이제 필요한 인터페이스를 모두 만들었으므로 구현을 시작해보겠습니다.

**MyTopic.java**

```java
public class MyTopic implements Subject {

    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX= new Object();

    public MyTopic(){
        this.observers=new ArrayList<>();
    }
    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("Null Observer");
        synchronized (MUTEX) {
            if(!observers.contains(obj)) observers.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        synchronized (MUTEX) {
            if (!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed=false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }

    }

    @Override
    public Object getUpdate(Observer obj) { ;
        return this.message;
    }

    public void postMessage(String msg){
        System.out.println("토픽이 메세지를 받음 : "+msg);
        this.message=msg;
        this.changed=true;
        notifyObservers();
    }

}
```

옵저버를 등록하거나, 등록해제하는 메소드의 구현은 간단합니다. 추가로 만든 *postMessage()* 메소드는 클라이언트가 토픽으로 메세지를 보내기위해 사용됩니다. 여기서 boolean타입의 changed 변수는 토픽의 변화를 게속 감지하고 옵저버에게 통지를 해줍니다. changed 변수가 없다면, 변화가 없을 때 외부에서 *notifyObservers()*를 호출 할 수 있기 때문입니다.

또한 notifyObservers() 메소드 안에는 동기화가 사용되어 병렬 프로그래밍에서의 경우 MUTEX 락을 사용해 메세지가 토픽에게 전송되기전에 Subject에 등록된 옵저버들에게만 통지를 해줍니다.

다음은 옵저버 구현 입니다.

**MyTopicSubscriber.java**

```java
public class MyTopicSubscriber implements Observer {

    private String name;
    private Subject topic;

    public MyTopicSubscriber(String name){
        this.name=name;
    }
    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: 새로운 메세지가 없습니다.");
        }else
            System.out.println(name+":: 메세지 수신 ::"+msg);
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic=sub;
    }
}
```

update() 메소드가 메세지를 가져오기 위해 Subject의 getUpdate() 메소드를 호출 합니다. 



다음은 테스트입니다

**ObserverPatternTests.java**

```java
public class ObserverPatternTests {
    public static void main(String[] args) {
        //subject 생성
        MyTopic topic = new MyTopic();

        //옵저버들 생성
        Observer obj1 = new MyTopicSubscriber("Obj1");
        Observer obj2 = new MyTopicSubscriber("Obj2");
        Observer obj3 = new MyTopicSubscriber("Obj3");

        //subject에 옵저버들 등록
        topic.register(obj1);
        topic.register(obj2);
        topic.register(obj3);

        //옵저버에도 subject 등록
        obj1.setSubject(topic);
        obj2.setSubject(topic);
        obj3.setSubject(topic);

        //업데이트 내용이 있는지 확인
        obj1.update();

        //subject에게 메세지 보내기
        topic.postMessage("안녕하세용~");
    }
}
```



**ObserverPatternTests.java**

```java
Obj1:: 새로운 메세지가 없습니다.
토픽이 메세지를 받음 : 안녕하세용~
Obj1:: 메세지 수신 ::안녕하세용~
Obj2:: 메세지 수신 ::안녕하세용~
Obj3:: 메세지 수신 ::안녕하세용~
```



## UML

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202020-04-15%20%EC%98%A4%ED%9B%84%2010.17.58.png?raw=true)

## 정리

- 책임연쇄패턴과 옵저버 패턴의 차이는?

  옵저버 패턴은 등록된 모든 핸들러 객체에게 동시에 통지를 해주고, 업데이트를 합니다.

  그러나 책임연쇄패턴은 체인 안에서 핸들러에 의해 하나 씩 통지가 됩니다. 

- 자바의 내장된 java.util.Observable과 Observer은?

  위에 내장된 클래스를 이용해서 옵저버 패턴을 구현할 수 있지만, 다중상속이 안되는 자바에서 옵저버를 구현하기 위해 상속을 사용한다는 것은 낭비일 수 있습니다. 현재 이 클래스는 JAVA SE 9 에서 Deprecated 된 상태입니다.

  > **Deprecated.**
  >
  > This class and the [`Observer`](https://docs.oracle.com/javase/10/docs/api/java/util/Observer.html) interface have been deprecated. The event model supported by `Observer` and `Observable` is quite limited, the order of notifications delivered by `Observable` is unspecified, and state changes are not in one-for-one correspondence with notifications. For a richer event model, consider using the [`java.beans`](https://docs.oracle.com/javase/10/docs/api/java/beans/package-summary.html) package. For reliable and ordered messaging among threads, consider using one of the concurrent data structures in the [`java.util.concurrent`](https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/package-summary.html) package. For reactive streams style programming, see the [`Flow`](https://docs.oracle.com/javase/10/docs/api/java/util/concurrent/Flow.html) API.

