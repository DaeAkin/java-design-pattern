# 데코레이터(decorator) 패턴

데코레이터 패턴은 런타임시 객체의 기능을 변경하기 위해 사용 됩니다. 그와 동시에 다른 클래스의 인스턴스들은 영향을 받지 않으며, 독립적인 객체가 변경된 동작을 얻게 됩니다. 

데코레이터 패턴은 구조 디자인 패턴 중에 하나이며 구현하기 위해 구성방법과 함께 추상 클래스나 인터페이스를 이용합니다.



## 데코레이터 패턴

스타벅스의 사이렌오더를 만든다고 가정해봅시다. 스타벅스 음료들의 공통적인 기능들이 있고, 음료마다 휘핑이나 샷 추가 등 넣을 수 있는 옵션등이 다릅니다. 이렇게 음료마다 **각기다른 동작을 구현**을 하려면 어떤 방법이 최선일까요?

> 상속을 활용한 구현 

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/%EB%8D%B0%EC%BD%94%EB%A0%88%EC%9D%B4%ED%84%B0%EC%B9%B4%ED%8E%98%EB%AC%B8%EC%A0%9C%EC%A0%90.png?raw=true)

상속을 이용하면 아메리카노 아메리카노샷2번추가 ,디카페인아메리카노 등등 **음료의 갯수 X 옵션** 만큼 객체가 많아져 <u>유지보수</u>가 어려워 집니다. 

또한 새로운 옵션이 추가되거나 가격이 변동 되었을 경우 클래스를 변경해야 하기 때문에 **OCP법칙**에도 위배되는 상황입니다. 




> 데코레이터 패턴을 쓰자!

객체의 동작을 물려받기 위해 상속 또는 구성을 사용할 수 있지만, 컴파일 때 완료되고, 모든 클래스 인스턴스에 적용 되버립니다. 그래서 런타임 시에 동작을 삭제하거나 새로운 동작을 추가할 수 없게 됩니다. 이 때 **데코레이터 패턴**을 사용하면 됩니다. 

> **컴파일 ? 런타임?** 
>
> 데코레이터 패턴을 사용하면 런타임 시 동작을 변경할 수 있습니다. 여기서 런타임은 무엇을 의미 할까요? 스프링의 특징 중 하나인 IOC 컨테이너에서 설정 파일만 바뀌면 재컴파일이 이루어지 않고 런타임시 설정을 바꿀 수 있습니다. 데코레이터 패턴도 이와 비슷합니다. 데코레이터 패턴을 사용하여 동작을 바꾸고 싶다면 클래스 A,B,C가 있다고 가정 할 때 A(B(C))  C(A(B)) 처럼 원하는대로 조합할 수 있습니다. 이런 걸 런타임 시 동작을 바꾼다고 합니다.



> 데코레이터 패턴 구조

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/%EB%8D%B0%EC%BD%94%EB%A0%88%EC%9D%B4%ED%84%B0%EA%B5%AC%EC%A1%B0.png?raw=true)





> 데코레이터로 변경할 클래스 구조!

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/%EB%8D%B0%EC%BD%94%EB%A0%88%EC%9D%B4%ED%84%B0%EC%93%B4%EC%B9%B4%ED%8E%98.png?raw=true)



## 컴포넌트 인터페이스

**Beverage.class**

```java
//Component 면서 ConcreteComponent
public abstract class Beverage {

    String description = "no desc";

    abstract int getCost();

    public String getDescription() {
        return this.description;
    }
}
```

Beverage 클래스는 음료의 공통 동작들을 정의하는 컴포넌트 클래스입니다. 여기서는 추상클래스를 이용하였습니다.

Beverage는 추상클래스여서 음료의 공통 동작들 또한 정의를 했기 때문에 Component 역할과 ConcreteComponent 역할을 동시에 할 수 있다고 말할 수 있습니다.

## 데코레이터

데코레이터 클래스는 컴포넌트 인터페이스를 구현하고 컴포넌트 인터페이스와 **HAS-A 관계**에 있습니다. 안에 있는 컴포넌트 변수는 자식 데코레이터 클래스에 접근할 수 있어 이 변수를 protected로 선언해줘야 합니다. 

이 클래스는 음료의 추가옵션등을 꾸며주는 역할을 한다고 생각하시면 됩니다. (ex 샷 추가, 휘핑추가 등)

**CompoundDecorator.class**

```java
//Decorator
public class CompoundDecorator extends Beverage {

    protected Beverage beverage;

    public CompoundDecorator(Beverage beverage) {
        this.beverage = beverage;
    }
    @Override
    public int getCost() {
        return this.beverage.getCost();
    }
}
```



## 콘크리트 데코레이터

기본 데코레이터 기능을 상속하여 컴포넌트들의 동작을 변경합니다. 

아메리카노에 Shot을 추가할 수 있는것 처럼 Shot 클래스를 만들어 보겠습니다. 

**Shot.class** 

```java
//ConcreteDecorator
public class Shot extends CompoundDecorator {

    int shotCost = 500;
    public Shot(Beverage beverage) {
        super(beverage);
    }

    @Override
    public int getCost() {
        return this.beverage.getCost()+shotCost;
    }
}
```



## 테스트

사이렌 오더가 다 만들어졌으니, 테스트를 해보겠습니다.

아메리카노를 주문해 샷을 2번 추가하는 테스트 입니다.

**Test**

```java
public class DecoratorClientTests {
     public static void main(String[] args) {
          Beverage am = new Shot(new Shot((new Americano())));
          System.out.println(am.getCost());
      }
}
```

**Output**

```java
아메리카노 가격 : 6000
```



## 데코레이터를 이용한 JDK 사례

InputStream이 데코레이터 패턴을 사용하고 있습니다.

```java
DataInputStream dis = new DataInputStream(new FileInputStream(file));
dis.readUnsignedByte();
```

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/%EB%8D%B0%EC%BD%94%EB%A0%88%EC%9D%B4%ED%84%B0%EC%9D%B8%ED%92%8B%EC%8A%A4%ED%8A%B8%EB%A6%BC.png?raw=true)