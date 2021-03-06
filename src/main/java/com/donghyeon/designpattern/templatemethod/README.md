# Template Method Pattern 

템플릿 메소드 패턴은 자식클래스가 사용할 **뼈대와, 실행순서**를 정의하는 디자인패턴입니다.

집을 짓는다고 가정해보겠습니다. 집을 짓기 위해서는

1. 기반을 다지고
2. 기둥을 세우고
3. 벽을 세우고
4. 창문을 만들고

총 이런 과정이 필요합니다. **여기서 중요한 점은 기반을 다지기전엔 창문을 먼저 만들 순 없으니**, 먼저 기반을 다지는 **순서**를 꼭 지켜야 합니다. 이런 경우 템플릿 메소드를 사용해서 각각 다른 집을 지을 때마다 이런 공통적인 4가지 단계의 **순서를 보장**해 줄 수 있습니다.

그렇지만 모든 집이 똑같은 재료의 기둥과 벽을 사용하는건 아니겠죠? 집이 나무로 이루어졌건, 유리로 이루어졌건 집을 만드는 기초는 똑같지만 **재료**는 달라질 수 있습니다. 

이렇게 어느 부분이 변하지 않고, 변하는 부분인지 정해야 합니다. 변하지 않는 부분(기반 다지는 곳,유리창 만드는 곳) 추상클래스 안에 구현을 해주고, 변하는 부분(기둥의 재료, 벽의 재료 등)은 구현을 자식에게 맡기면 됩니다. 변하는 부분을 **훅 메소드**라고 하는데, 클라이언트가 이를 구현 합니다.

## 템플릿 메소드 추상 클래스 

**HouseTemplate.class**

```java
public abstract class HouseTemplate {

    //템플릿 메소드, 오버라이드 하면 안됨.!
    public final void buildHouse(){
        buildFoundation();
        buildPillars();
        buildWalls();
        buildWindows();
        System.out.println("집 완성!");
    }

    private void buildWindows() {
        System.out.println("유리창 만들기");
    }

    // 자식클래스가 구현해야함
    public abstract void buildWalls();
    public abstract void buildPillars();

    private void buildFoundation() {
        System.out.println("모래와 시멘트로 기반 다지기");
    }
}
```

`buildHouse()` 가 템플릿 메소드이며 실행할 메소드를 순서에 맞게 실행합니다.



## 템플릿 메소드 구현 클래스

```java
public class WoodenHouse extends HouseTemplate {

    @Override
    public void buildWalls() {
        System.out.println("나무벽 만들기");
    }

    @Override
    public void buildPillars() {
        System.out.println("나무 기등 만들기");
    }
}
```

나무로 된 집을 만드는 WoodenHouse 클래스 입니다.

```java
public class GlassHouse extends HouseTemplate {
    @Override
    public void buildWalls() {
        System.out.println("유리벽 만들기");
    }
    @Override
    public void buildPillars() {
        System.out.println("유리 기둥 만들기");
    }
}
```

유리로 된 집을 만드는 GlassHouse 입니다.

## 테스트 

**TemplateMethodTests.class**

```java
public class TemplateMethodTests {
     public static void main(String[] args) {
         WoodenHouse woodenHouse = new WoodenHouse();
         woodenHouse.buildHouse();
       
         System.out.println("************");

         GlassHouse glassHouse = new GlassHouse();
        glassHouse.buildHouse();
      }
}
```

**결과**

```java
모래와 시멘트로 기반 다지기
나무 기등 만들기
나무벽 만들기
유리창 만들기
집 완성!
************
모래와 시멘트로 기반 다지기
유리 기둥 만들기
유리벽 만들기
유리창 만들기
집 완성!
```

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/%ED%85%9C%ED%94%8C%EB%A6%BF%EB%A9%94%EC%86%8C%EB%93%9C%ED%8C%A8%ED%84%B4UML.png?raw=true)

대부분 자식클래스가 부모클래스 메소드를 호출하지만, 템플릿 메소드 패턴은 부모클래스가 자식클래스를 호출하는 IOC(Inversion of control)의 특징을 갖고 있습니다.

## 사용 사례

- java.io.InputStream, java.io.OutputStream,java.io.Reader,java.ioWriter의 abstract 메소드가 아닌 메소드들이 템플릿 메소드 패턴을 이용하고 있습니다.
- java.util.AbstractList,java,util,AbstractSet,java.util.AbstractMap의 abstract 메소드가 아닌 메소드들이 템플릿 메소드 패턴을 이용하고 있습니다.

**InputStream의 read() 템플릿 메소드**

```java
public abstract class InputStream implements Closeable {
	    public int read(byte[] var1, int var2, int var3) throws IOException {
        if (var1 == null) {
            throw new NullPointerException();
        } else if (var2 >= 0 && var3 >= 0 && var3 <= var1.length - var2) {
            if (var3 == 0) {
                return 0;
            } else {
                int var4 = this.read();
                if (var4 == -1) {
                    return -1;
                } else {
                    var1[var2] = (byte)var4;
                    int var5 = 1;

                    try {
                        while(var5 < var3) {
                            var4 = this.read();
                            if (var4 == -1) {
                                break;
                            }

                            var1[var2 + var5] = (byte)var4;
                            ++var5;
                        }
                    } catch (IOException var7) {
                    }

                    return var5;
                }
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
}
```



**InputStream의 자식클래스인 FileInputStream 클래스**

```java
public
class FilterInputStream extends InputStream {
    protected volatile InputStream in;
  
    public int read(byte b[], int off, int len) throws IOException {
        return in.read(b, off, len);
    }    
}
```

FileInputStream 클래스의 read 메소드는 구성 방법을 사용해 InputStream의 템플릿 메소드를 호출하는 모습을 볼 수 있습니다.

