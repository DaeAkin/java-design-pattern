# Mediator 패턴

GOF에 따르면 Mediator 패턴의 목적은 "서로 상호작용 하는 객체들을 캡슐화 함으로써 결합력을 낮추는 용도" 입니다. 

Mediator 패턴은 여러 개의 객체가 서로 상호작용 많은 경우 유용하게 사용할 수 있는 패턴입니다. 객체가 다른 객체를 직접적으로 사용한다면 유지보수성이 늘어나며, 확장하기에도 불리합니다. Mediator 패턴은 두 오브젝트를 중간에서 커뮤니케이션 해주는 패턴입니다.

공항관제탑이 mediator 패턴을 잘 이용한 사례입니다. 공항관제실에서 비행기마다 통신해서 착륙을 조절해주기 때문입니다. 중개자는 두개의 객체 사이에서 라우터 역할을 맡으며, 중개 방법에 대해서는 자신만의 로직을 갖고 있습니다.

이렇게 각 객체마다 통신하는 객체를 동료객체라고 부릅니다. 보통 인터페이스나 추상클래스로 이루어져 있으며, 통신방법을 정의하고, 

The system objects that communicate each other are called Colleagues. Usually we have an [interface or abstract class](https://www.journaldev.com/1607/difference-between-abstract-class-and-interface-in-java) that provides the contract for communication and then we have concrete implementation of mediators.

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

## Mediator





before after 추가하고, 클래스 UML도 그리고