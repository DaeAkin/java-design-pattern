# Mediator 패턴

GOF에 따르면 Mediator 패턴의 목적은 **"서로 상호작용 하는 객체들을 캡슐화 함으로써 결합력을 낮추는 용도"** 입니다. 

Mediator 패턴은 여러 개의 객체가 서로 **상호작용 많은 경우** 유용하게 사용할 수 있는 패턴입니다. 객체가 다른 객체를 직접적으로 사용한다면 유지보수성이 늘어나며, 확장하기에도 불리합니다. Mediator 패턴은 두 오브젝트를 중간에서 커뮤니케이션 해주는 패턴입니다.

공항관제탑이 mediator 패턴을 잘 이용한 사례입니다. 공항관제실에서 비행기마다 통신해서 착륙을 조절해주기 때문입니다. 중개자는 두개의 객체 사이에서 라우터 역할을 맡으며, 중개 방법에 대해서는 자신만의 로직을 갖고 있습니다.

예를들어 그룹 채팅을 할 수 있는 채팅 어플을 만든다고 가정하면, 모든 유저는 이름을 만든다음 메세지를 보내거나 받을 수 있습니다. 유저가 보낸 메세지는 그룹에 있는 모든 유저들이 받을 수 있어야 합니다.

> Mediator 패턴 구조

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/Mediator%ED%8C%A8%ED%84%B4%EA%B5%AC%EC%A1%B0.png?raw=true)

## Mediator 인터페이스

실제 구현에 사용할 Mediator 인터페이스를 만들어 보겠습니다.

**ChatMediator.java**

```java
//Mediator 뼈대 만들기
public interface ChatMediator {
    public void sendMessage(String msg,User user);
    void addUser(User user);
}
```



## Mediator 동료(**Colleague**) 인터페이스

User가 메세지를 보내거나 받을 수 있습니다.

**User.java**

```java
public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}
```

다른 User와 통신하기 위해 User 객체는 Mediator 객체를 참조하고 있습니다.

이 User 객체는 Producer 역할을 하면서 동시에 Consumer의 역할도 하고 있습니다.

## Mediator 구현하기

앞전에 만들었던 Mediator 인터페이스를 구현 해보겠습니다. 여기서 구현할 객체는 리스트에 User 객체를 담고 User 사이를 중개해주는 역할을 합니다.

**ChatMediatorImpl.java**

```java
public class ChatMediatorImpl implements ChatMediator {

    private List<User> users;

    public ChatMediatorImpl(){
        this.users=new ArrayList<>();
    }

    @Override
    public void addUser(User user){
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for(User u : this.users){
            if(u != user){
                u.receive(msg);
            }
        }
    }
}
```

## Mediator 동료(Colleague) 클래스 구현하기

**UserImpl.java**

```java
public class UserImpl extends User {

    public UserImpl(ChatMediator med, String name) {
        super(med, name);
    }

    @Override
    public void send(String msg){
        System.out.println(this.name+": Sending Message="+msg);
        mediator.sendMessage(msg, this);
    }
    @Override
    public void receive(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }

}
```

send() 메소드를 보면 mediator에 있는 sendMessage()를 요청해서 User에게 메세지를 보냅니다. UserImpl 클래스는 mediator가 어떻게 처리하는지 알 필요가 없습니다.

## 테스트

**MediatorTests.java**

```java
public class MediatorTests {
    public static void main(String[] args) {
        ChatMediator mediator = new ChatMediatorImpl();
        User user1 = new UserImpl(mediator, "Donghyeon");
        User user2 = new UserImpl(mediator, "SeoHyun");
        User user3 = new UserImpl(mediator, "Hodong");
        User user4 = new UserImpl(mediator, "Tae");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("안녕하세요!");
    }
}
```

**결과**

```java
Donghyeon: Sending Message=안녕하세요!
SeoHyun: Received Message:안녕하세요!
Hodong: Received Message:안녕하세요!
Tae: Received Message:안녕하세요!
```



## 클래스 UML



![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/Mediator%EC%98%88%EC%A0%9CUML.png?raw=true)





![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/Mediator%EC%98%88%EC%A0%9C%EA%B5%AC%EC%A1%B0.png?raw=true)



## 정리

- Mediator 패턴은 객체간의 상호작용이 복잡할 때 유용합니다. 상호작용 하는 부분을 한군데서 관리할 수 있게 됩니다.
- JMS(Java MEssage Service)는 다른 애플리케이션으로 데이터를 받거나 보내기 위해서 옵저버 패턴과 Mediator 패턴을 같이 사용 합니다.
- **mediator 패턴을 단지 객체간의 결합력을 낮추기 위해서 사용하면 안됩니다. mediator 객체가 많아지면 유지보수 하기가 힘들어 집니다.**