# 싱글톤 패턴 

싱글톤 패턴은 **Gangs Of Four(GOF)** 디자인 패턴 중 한개이며 **객체 생성할 때 쓰는 디자인 패턴**입니다. 싱글톤의 정의를 들여다보면 엄청 간단한 디자인 패턴인 것 같지만 실제로 구현을 해보면 신경을 많이 써야하는 패턴입니다.

자바의 싱글톤 패턴의 구현은 개발자들 사이에서 항상 논란이 있었습니다. 여기서는 싱글톤 디자인 패턴 규칙에 대해서 알아보고, 다른 방법의 싱글톤 디자인패턴의 구현와 적절한 예를 살펴보겠습니다.



## 싱글톤 패턴 

- 싱글톤 패턴은 클래스의 인스턴스화를 억제하고 오직 한개의 클래스 인스턴스만 자바 가상머신에 존재하는 패턴입니다.
- 싱글톤 패턴은 반드시 어디서든 클래스 인스턴스에 접근할 수 있는 글로벌하게 액세스할 수 있는 지점을 제공해줘야 합니다.
- 싱글톤 패턴은 로깅이나, 드라이버 오브젝트, 캐싱, 그리고 스레드 풀에 사용됩니다.
- 싱글톤 패턴은 추상 팩토리 패턴이나 빌더 패턴 등 다른 패턴들이 사용하기도 합니다.
- 싱글톤 패턴은 자바의 코어 클래스인 java.lang.RunTime 이 사용하고 있는 패턴이기도 합니다.

## 싱글톤 패턴 구현하기

싱글패톤을 구현하기위해서 각기다른 접근법을 사용하지만 , 다음의 공통 컨셉은 동일합니다. 

- 다른 클래스에서 클래스의 인스턴스를 생성하는걸 막기위해 생성자를 private 처리해줍니다. 
- 오직 하나의 클래스의 인스턴스를 갖기 위해서 private static 변수를 해당 클래스에 선언합니다. 
- 해당 클래스를 리턴하는 public static 메소드를 만듭니다. 이 메소드가 바로 다른 클래스에서도 글로벌하게 액세스 할 수 있는 역할을 해주는 클래스 입니다. 

이제 싱글톤 패턴을 구현해보겠습니다. 



### 즉시 초기화(Eager initialization)

즉시 초기화 하는 방법은 클래스가 로딩하는 시점에 클래스가 만들어 집니다. 이 방법이 싱글톤을 만드는 가장 쉬운 방법이지만 이 클래스가 사용을 안할 수도 있음에도 불구하고 인스턴스를 만든다는 단점이 있습니다.(자원낭비가 되겠죠?)  다음은 즉시 초기화의 예제 입니다.

```java
public class EagerInitializationSingleton {

    private static final EagerInitializationSingleton instance = new EagerInitializationSingleton();

    private EagerInitializationSingleton() {
    }

    public static EagerInitializationSingleton getInstance() {
        return instance;
    }
}
```

만약 클래스 안에 리소스들이 많지 않다면 자원낭비가 심하진 않으므로 이 방법을 사용해도 됩니다. 그러나 많은 시나리오들이 싱글톤 클래스를 파일 시스템이나, 데이터베이스 커넥션 등 많은 리소스를 잡아 먹는 클래스를 싱글톤으로 만듭니다. 만약 사용하지 않으면 이런 리소스들을 낭비하기 때문에 getInstance() 메소드가 호출되기 전까지는 클래스 만드는 작업을 하지 않는게 좋을 것 같습니다. 또한 이. 방법은 Exception을 핸들링 할 방법이 없습니다.

### Static block를 이용한 초기화(Static block initialization)

Static block 초기화 구현은 static block을 이용해서 인스턴스를 만든다는 점을 빼고는 위에 있는 즉시 초기화와 비슷합니다.

그러나 Excpetion 핸들링이 가능합니다.

```java
public class StaticBlockSingleton {
    private static StaticBlockSingleton instance;

    private StaticBlockSingleton() {
    }
    //static block initialization for exception handling
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}
```

Static block을 이용한 초기화와 즉시 초기화 모두 해당 클래스를 사용하기 전에 인스턴스를 만들어 버리므로 실제로 사용하기엔 효과적인 방법은 아닙니다. 

### 지연 초기화(Lazy initialization)

```java
public class LazyInitialization {
    private static LazyInitialization instance;

    private LazyInitialization() {
    }

    public static LazyInitialization getInstance() {
        if(instance == null) {
            instance = new LazyInitialization();
        }
        return instance;
    }
}
```

이 방법은 싱글스레드 기반 환경에서는 잘 작동하지만, RX같은 멀티스레드 시스템경우 if 문을 동시에 실행하면 문제가 발생합니다. 

### 스레드세이프한 싱글톤(Thread Safe Singleton)

스레드 세이프한 싱글톤을 만들기 쉬운 방법은 getInstance() 메소드에  synchronized를 붙여줘서 하나의 스레드씩 실행되게 하는 방법입니다. 

```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }
    public static synchronized ThreadSafeSingleton getInstance() {
        if(instance == null) {
            return new ThreadSafeSingleton();
        }
        return instance;
    }
}
```

