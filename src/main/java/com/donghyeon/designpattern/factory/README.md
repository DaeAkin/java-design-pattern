# 팩토리 패턴

팩토리 패턴은은 여러 개의 자식 클래스를 갖는 부모 클래스가 있고, 입력에 따라 자식클래스 중 하나를 반환해야 할 때 사용됩니다. 

첫번 째로 팩토리 패턴을 어떻게 구현하는지와 이 패턴의 장점을 알아보겠습니다. JDK에서 팩토리 패턴을 사용한 사례들이 몇개 있습니다. 팩토리 패턴은 팩토리 메소드 패턴으로도 알려져있습니다.

### 팩토리 패턴의 부모 클래스

팩토리 패턴의 부모 클래스는 인터페이스나, 추상클래스 또는 그냥 클래스일 수 있습니다. 이번 예제에서는 추상 부모클래스를 이용해보겠습니다. 

> 테스트를 위해서 toString()을 오버라이드 했습니다.

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

### 팩토리 패턴 자식 클래스

PC와 Server **자식 클래스**가 있다고 가정해봅시다.

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

위 아래 클래스 모두 **Computer 클래스를 상속**했습니다.

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

### 팩토리 클래스

이제 부모클래스와 자식클래스를 준비했으니 팩토리 클래스를 구현하겠습니다.

```java
public class ComputerFactory {

	public static Computer getComputer(String type, String ram, String hdd, String cpu){
		if("PC".equalsIgnoreCase(type)) return new PC(ram, hdd, cpu);
		else if("Server".equalsIgnoreCase(type)) return new Server(ram, hdd, cpu);
		
		return null;
	}
}
```

팩토리 패턴의 중요한 **포인트**는 다음과 같습니다.

- 팩토리 클래스를 싱글톤이나 static으로 자식클래스를 **리턴**하는 걸 가지고 있어야 합니다.
- **입력 파라미터에 따라** 각각의 다른 자식클래스들이 리턴됩니다. `getComputer` 메소드는 팩토리 메소드 입니다.

### 테스트

다음은 팩토리 클래스를 테스트하는 코드 입니다.

```java
public class TestFactory {
    public static void main(String[] args) {
        Computer pc = ComputerFactory.getComputer("pc","2 GB","500 GB","2.4 GHz");
        Computer server = ComputerFactory.getComputer("server","16 GB","1 TB","2.9 GHz");
        System.out.println("Factory PC Config::"+pc);
        System.out.println("Factory Server Config::"+server);
    }
}
```

**결과**

```
Factory PC Config::RAM= 2 GB, HDD=500 GB, CPU=2.4 GHz
Factory Server Config::RAM= 16 GB, HDD=1 TB, CPU=2.9 GHz
```

### 팩토리 패턴의 장점 

- 팩토리 패턴은 인터페이스에 대한 접근 방식을 제공합니다.
- 팩토리 패턴은 실제 구현 클래스의 인스턴스를 제거하여 의존성을 제거하고 확장하기 쉽게 만듭니다. 예를 들어 PC 클래스 구현을 쉽게 변경할 수 있습니다.
- 팩토리 패턴은 상속을 통해 클래스 간의 추상화를 제공합니다.

### JDK에서 팩토리 패턴 사용 사례 

- java.util.Canlendar , Resource , NumberFormat 클래스 들의 `getInstance()` 메소드들이 팩토리 패턴을 사용합니다.
- Boolean 이나 Integer등 wrapper 클래스에 있는 `valueOf()` 메소드가 사용합니다.