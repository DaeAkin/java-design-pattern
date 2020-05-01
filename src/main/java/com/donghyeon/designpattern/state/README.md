# State Pattern

객체의 행동을 객체의 상태에 따라 변경하고 싶다면, 객체안에 state 변수를 갖고 있으면 됩니다. 그러면 **if-else** 조건문을 사용해 상태에 따라 다른 동작을 할 수 있습니다. State 패턴은 `Context` 와 `State` 를 이용하여 체계적이고 결합력이 낮은 방법을 제공합니다.

State 패턴의 **Context** 는 State 의 구현 객체를 하나 참조하고 있는 클래스 입니다. 이 Context는 요청을 처리하기 위해 State 객체로 전달해줍니다.

예를 들어, TV를 켜고 끌 수 있는 버튼만 있는 TV 리모콘이 있다고 가정해보겠습니다. 이 버튼을 누르면, TV가 켜져있으면 TV가 꺼지고, TV가 꺼져있으면 TV가 켜집니다.

이 로직은 다음과 같이 간단히 구현할 수 있습니다.

**TVRemoteBasic.java**

코드~

코드를 보면 클라이언트는 "ON" , "OFF" 처럼 특정한 값을 사용하고 있습니다. 미래에 "ON","OFF" 말고 상태가 증가한다면, 구현체와 클라이언트 사이는 강한 결합력이 생기게되고, 클라이언트 코드는 유지보수하거나, 확장에 어려움을 겪게 됩니다.



## State Pattern을 사용해보자

먼저 State 인터페이스를 만들어 메소드를 선언하고, State 구현 클래스와 Context 클래스를 만들어 보겠습니다.

**State.java**

코드~

### State 구현체 만들기

이번 예제에서는 두개의 State 클래스를 만들어 보겠습니다. TV를 **켜는** 클래스와 TV를 **끄는** 클래스 입니다.

**TVStartState.java**

~코드

**TVStopState.java**

~코드

이제 state에 따라 동작을 하는 Context를 구현하면 됩니다.

### State에 사용할 Context클래스 구현하기

**TVContext.java**

코드~

Notice that Context also implements State and keep a reference of its current state and forwards the request to the state implementation.

TVContext 클래스는 State인터페이스를 구현하며 보면 내부에 State 객체를 갖고 있어 



## 테스트



**TVRemote.java**

~코드