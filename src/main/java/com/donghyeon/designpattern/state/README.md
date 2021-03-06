# State Pattern

객체의 행동을 객체의 상태에 따라 변경하고 싶다면, 객체안에 state 변수를 갖고 있으면 됩니다. 그러면 **if-else** 조건문을 사용해 상태에 따라 다른 동작을 할 수 있습니다. State 패턴은 `Context` 와 `State` 를 이용하여 체계적이고 결합력이 낮은 방법을 제공합니다.

State 패턴의 **Context** 는 State 의 구현 객체를 하나 참조하고 있는 클래스 입니다. 이 Context는 요청을 처리하기 위해 State 객체에게 요청을 전달해줍니다.

예를 들어, TV를 켜고 끌 수 있는 버튼만 있는 TV 리모콘이 있다고 가정해보겠습니다. 이 버튼을 누르면, TV가 켜져있으면 TV가 꺼지고, TV가 꺼져있으면 TV가 켜집니다.

이 로직은 다음과 같이 간단히 구현할 수 있습니다.

**BeforeTvRemote.java**

```java
public class BeforeTvRemote {
    private String state="";

    public void setState(String state){
        this.state=state;
    }

    public void doAction(){
        if(state.equalsIgnoreCase("ON")){
            System.out.println("TV가 켜짐.");
        }else if(state.equalsIgnoreCase("OFF")){
            System.out.println("TV가 꺼짐");
        }
    }

    public static void main(String args[]){
        BeforeTvRemote remote = new BeforeTvRemote();

        remote.setState("ON");
        remote.doAction();

        remote.setState("OFF");
        remote.doAction();
    }
}

```

코드를 보면 클라이언트는 **"ON"** , **"OFF"** 처럼 특정한 값을 사용하고 있습니다. 나중에 "ON","OFF" 말고 상태가 증가한다면, 구현체와 클라이언트 사이는 강한 결합력이 생기게되고, 클라이언트 코드는 유지보수하거나, 확장에 어려움을 겪게 됩니다.



## State Pattern을 사용해보자

먼저 State 인터페이스를 만들어 메소드를 선언하고, State 구현 클래스와 Context 클래스를 만들어 보겠습니다.

**State.java**

```java
public interface State {
    void doAction(TVContext state);
}
```

### State 구현체 만들기

이번 예제에서는 두개의 State 클래스를 만들어 보겠습니다. TV를 **켜는** 클래스와 TV를 **끄는** 클래스 입니다.

**TVStartState.java**

```java
public class TVStartState implements State {
    @Override
    public void doAction(TVContext state) {
        state.setTvState(new TVStopState());
        System.out.println("TV 켜짐");
    }
}
```

**TVStopState.java**

```java
public class TVStopState implements State {
    @Override
    public void doAction(TVContext state) {
        state.setTvState(new TVStartState());
        System.out.println("TV가 꺼짐");
    }
}
```

###  Context클래스 구현하기

이제 state에 따라 동작을 하는 Context를 구현하면 됩니다.

**TVContext.java**

```java
public class TVContext {

    private State tvState; //현재 State 상황, 요청전달

    public void setTvState(State tvState) {
        this.tvState = tvState;
    }

    public void doNext(TVContext state) {
        this.tvState.doAction(state);
    }
}
```

TVContext 클래스는 내부에는 State 객체를 갖고 있어 State 구현체에게 요청을 전달해줍니다. 

## 테스트

**StatePatternTests.java**

```java
public class StatePatternTests {

    public static void main(String[] args) {
        TVContext context = new TVContext();
        State tvStartState = new TVStartState();
        context.setTvState(tvStartState);

        context.doNext(context);
        context.doNext(context);
    }
}
```



**결과**

```java
TV 켜짐
TV가 꺼짐
```



## 구조

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/StatePatternUML.png?raw=true)

## State 패턴의 이점

State 패턴을 사용하면 다형성을 눈으로 확인할 수 있다는 장점이 있습니다. 오류를 낼 가능성이 낮아지고, 추가로 동작을 만들기가 쉬워집니다. 그리하여 유지보수성과 유연성이 높아져, 코드가 튼튼해집니다. 또한 이 예제에서 State 패턴은 if-else 문이나 switch-case 문을 사용하지 않았습니다.

State 패턴은 전략패턴(Strategy Pattern)과 매우 유사 합니다.