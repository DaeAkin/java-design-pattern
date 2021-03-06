# 빌더패턴

빌더 패턴은 팩토리 패턴이나 추상 팩토리 패턴에서 객체가 많은 속성들을 갖고 있을 때 발생하는 문제를 해결하기 위해서 만들어졌습니다. 

빌더패턴의 장점은 **여러 개의 많은 필드 중에 값을 주고 싶은 필드만** 골라서 객체를 리턴할 수 있다는 장점이 있습니다.

## 빌더 패턴

- 첫번 째로 **내부안에 static 클래스**를 만들고 외부 클래스에 있는 인자들을 전부 복사해와야 합니다. 외부 클래스 이름이 **Computer라면**, 빌더 클래스는 **ComputerBuilder로** 지어야하는 **네이밍 컨벤션**을 따라야 합니다.
- 자바 빌더 클래스는 모든 필요한 필드들은 public으로 된 생성자로 만들어야 합니다.
- 자바 빌더 클래스는 선택적인 필드를 만들어주는 메소드가 있어야하며 그 필드를 만들었다면 같은 Builder 오브젝트를 리턴해야 합니다.
- 마지막으로 build()를 만들어 객체를 리턴하는 메소드가 있어야합니다. 그러므로 **외부 클래스의 생성자를 private**로 만들어야 합니다.



## 빌더 패턴 예제

다음은 Computer 클래스안에 ComputerBuilder 클래스가 있는 예제 입니다.

**Computer.java**

```java
public class Computer {

    //required 
    private String HDD;
    private String RAM;

    //optional 
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;


    public String getHDD() {
        return HDD;
    }

    public String getRAM() {
        return RAM;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    private Computer(ComputerBuilder builder) {
        this.HDD=builder.HDD;
        this.RAM=builder.RAM;
        this.isGraphicsCardEnabled=builder.isGraphicsCardEnabled;
        this.isBluetoothEnabled=builder.isBluetoothEnabled;
    }

    //Builder Class
    public static class ComputerBuilder{


        private String HDD;
        private String RAM;

        // optional 
        // 다음은 선택적 필드이므로 값을 가지는건 자유임.
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        public ComputerBuilder(String hdd, String ram){
            this.HDD=hdd;
            this.RAM=ram;
        }

        public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
            this.isGraphicsCardEnabled = isGraphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
            this.isBluetoothEnabled = isBluetoothEnabled;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }

}
```

Computer 클래스는 오직 getter 메소드만 있고 public으로 된 생성자가 없어서 Computer 클래스 자체로는 Computer 객체를 만들지 못합니다.

## 빌더 패턴 테스트

다음은 빌더 패턴을 테스트 해보겠습니다.

```java
public class TestBuilderPattern {

    public static void main(String[] args) {

        Computer com1 = new Computer.ComputerBuilder("500 GB", "2 GB")
                .setBluetoothEnabled(true)
                .setGraphicsCardEnabled(true).build();

        //블루투스랑 그래픽카드를 사용하지 않으면 이렇게 만든다.
        Computer com2 = new Computer.ComputerBuilder("1 TB" , "16 GB")
                .build();
    }
}
```



## 아 💢 필드가 너무 많아요 언제 다 만들어요? 🤬

만약 Computer 객체에 다음과 같이 선택적 필드가 엄청 많다면 코드량이 늘어나게 됩니다. 하지만 이런 방법을 해결해주기 위해 [Lombok](https://projectlombok.org/) 이 있습니다.

Lombok을 설치하시고 라이브러리를 넣어주시면 됩니다.(다른 블로그를 참조해주세요.)

**ComputerLombok.class**

```java
@Builder
@Getter
public class ComputerLombok {
    //required
    private String HDD;
    private String RAM;

    //optional 
    private boolean isGraphicsCardEnabled;
    private boolean isBluetoothEnabled;
}
```

해당 클래스에 @Builder 어노테이션만 붙여주면 Lombok이 알아서 만들어줍니다.

@Getter 어노테이션은 모든 필드에 관한 getter 메소드를 만들어주는 작업을 해줍니다.

이렇게 만드시면 코드량이 엄청 줄게 됩니다.