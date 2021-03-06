# 추상 팩토리 패턴

추상 팩토리 패턴은 팩토리 패턴과 비슷하며 **팩토리들의 팩토리 역할**입니다. 팩토리 패턴은 한개의 팩토리 클래스로 **if~else 문**을 또는 **swich 문**을 이용해서 **각각 다른 자식클래스**를 리턴하는걸 알 수 있습니다. 만약 자식 클래스가 더 많아지면 if~else 코드가 더 길어지겠죠? 

**추상 팩토리 패턴**은 이런 **if~else 괄호를 없애고** 각 자식클래스에 대한 팩토리 클래스와 입력에 따라 자식 클래스를 리턴하는 추상클래스를 갖습니다.

팩토리 패턴에서 사용하던 예제 클래스를 그대로 사용하겠습니다.

## 추상 팩토리 패턴 부모클래스와 자식 클래스

**Computer.java**

```java
public abstract class Computer {
	
	public abstract String getRAM();
	public abstract String getHDD();
	public abstract String getCPU();
	
	@Override
	public String toString(){
		return "RAM= "+this.getRAM()+", HDD="+this.getHDD()+", CPU="+this.getCPU();
	}
}
```

**PC.java**

```java
public class PC extends Computer {

	private String ram;
	private String hdd;
	private String cpu;
	
	public PC(String ram, String hdd, String cpu){
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}
	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}
```

**Server.java**

```java
public class Server extends Computer {

	private String ram;
	private String hdd;
	private String cpu;
	
	public Server(String ram, String hdd, String cpu){
		this.ram=ram;
		this.hdd=hdd;
		this.cpu=cpu;
	}
	@Override
	public String getRAM() {
		return this.ram;
	}

	@Override
	public String getHDD() {
		return this.hdd;
	}

	@Override
	public String getCPU() {
		return this.cpu;
	}

}
```

### 각각 자식클래스에 관한 팩토리 클래스 만들기

첫번 째 로 할일은 추**상 팩토리 인터페이스** 또는 **추상클래스**를 만드는 것입니다.

**ComputerAbstractFactory.java**

```java
public interface ComputerAbstractFactory {
     Computer createComputer();
}
```

**createCompter()** 메소드는 부모 클래스의 인스턴스인 **Computer** 를 리턴합니다. 이제 팩토리 클래스는 이 인터페이스를 구현하고 해당 자식 클래스를 반환합니다.

**PCFactory.java**

```java
public class PCFactory implements ComputerAbstractFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public PCFactory(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }
    @Override
    public Computer createComputer() {
        return new PC(ram,hdd,cpu);
    }

}
```

Server 클래스도 비슷하게 팩토리 클래스를 만들어 줍니다.

**ServerFactory.java**

```java
public class ServerFactory implements ComputerAbstractFactory {

    private String ram;
    private String hdd;
    private String cpu;

    public ServerFactory(String ram, String hdd, String cpu){
        this.ram=ram;
        this.hdd=hdd;
        this.cpu=cpu;
    }

    @Override
    public Computer createComputer() {
        return new Server(ram,hdd,cpu);
    }

}
```

이제 자식 클래스를 생성하기 위한 클래스를 만들겠습니다.

**ComputerFactoryForAbstractFactory.java**

```java
public class ComputerFactoryForAbstractFactory {
    public static Computer getComputer(ComputerAbstractFactory factory){
        return factory.createComputer();
    }
}
```

**getComputer** 메소드가 인자로 **ComputerAbstractFactory**를 갖고 **Computer** 객체를 리턴합니다. 


> 앞전에 이미 ComputerFactory 클래스를 만들었기 때문에, 구분하기 위해서 새롭게 AbsractFactory 이름을 덧붙인 팩토리 클래스를 만들었습니다. 실무 코드라면 ComputerFacotry 이름을 가진 클래스에다 작성하는 것이 올바른 방법 입니다.

이제 자식 클래스의 인스턴스를 얻기위해 어떻게 추상 팩토리 패턴을 사용하는지 간단한 테스트코드를 작성해보겠습니다.

**TestAbstractFactoryPattern.java**

```java
public class TestAbstractFactoryPattern {

     public static void main(String[] args) {
        testAbstractFactory();
      }

    private static void testAbstractFactory() {
        Computer pc = ComputerFactoryForAbstractFactory.getComputer(new PCFactory("2 			  GB","500 GB","2.4 GHz"));
        Computer server = ComputerFactoryForAbstractFactory.getComputer(new ServerFactory("16 GB","1 TB","2.9 GHz"));
        System.out.println("AbstractFactory PC Config::"+pc);
        System.out.println("AbstractFactory Server Config::"+server);
    }
}
```

**결과**

```java
AbstractFactory PC Config::RAM= 2 GB, HDD=500 GB, CPU=2.4 GHz
AbstractFactory Server Config::RAM= 16 GB, HDD=1 TB, CPU=2.9 GHz
```



## 추상 팩토리 메서드 패턴으로써 얻을 수 있는 것

추상 팩토리 패턴은 구현 보다는 인터페이스의 접근을 합니다. 추상 팩토리 패턴은 "**팩토리들의 팩토리**" 이며 쉽게 클래스들을 확장할 수 있습니다. 예를 들어 **다른 자식 클래스**인 "**Laptop**"을 추가하고자 하면 그냥 LaptopFactory 팩토리 클래스를 만들면 됩니다. 

## JDK에서 추상 팩토리 메서드가 쓰인 곳

- javax.xml.parsers.DocumentBuilderFactory#newInstance()
- javax.xml.transform.TransformerFactory#newInstance()
- javax.xml.xpath.XPathFactory#newInstance()



### PCFactory , ServerFactory 말고 그냥 PC() , Server()로 객체를 만들면 안되나요?

예를 들어 또 다른 클래스인 PCB 클래스를 만들었는데 코드를 다 짜고 PCB 클래스를 PCA로 바꾸고 싶으면 이미 짜여진 코드인 클라이언트 코드가 영향 받기 때문에 클라이언트 코드를 수정해줘야 합니다. 

만약 추상화를 하지않고 직접적으로 클래스를 줘버린다면, **그 클래스가 변경이 있을 경우 전부다** 바꿔줘야 합니다. 

