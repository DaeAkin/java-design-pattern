# 구성패턴(Composite pattern)

구성패턴은 여러 개의 객체들로 구성된 복합 객체와 단일 객체를 클라이언트에서 구별 없이 다루게 해주는 패턴입니다.

구성 패턴은 전체 구조를 나타내기 위해 객체를 <u>트리 구조</u>로 구성합니다. 구성은 사용하면 클라이언트가 독립적인 객체와 객체 구성을 균열하게 처리할 수 있습니다.

- Component : 컴포넌트는 객체와 메소드에 접근하기 위한 구성을 목적으로 한 인터페이스 입니다. 
- Leaf : leaf 클래스는 구체적인 컴포넌트 객체입니다. leaf 클래스는 컴포넌트 인터페이스를 구현합니다. 
- Composite : 컴포넌트를 구현하고 leaf 객체를 갖음.

이번 예제는 삼각형,원,사각형 등 도형을 만들어서 이 도형들을 같은 색으로 색칠하는 예제를 사용하겠습니다.

## 컴포넌트

**Shape.java**

```java
public interface Shape {	
	public void draw(String fillColor);
}
```

Shape 인터페이스는 leaf와 composite가 사용할 공통 메소드를 정의하는 클래스입니다. 

여기서는 모든 도형들의 공통 동작인 draw() 메소드를 정의했습니다.

## Leaf 

**Triangle.java**

```java
public class Triangle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing Triangle with color "+fillColor);
	}

}
```

**Circle.java**

```java
public class Circle implements Shape {

	@Override
	public void draw(String fillColor) {
		System.out.println("Drawing Circle with color "+fillColor);
	}

}
```

## Composite 

**Drawing.java**

```java
public class Drawing implements Shape{

    //Shape의 컬렉션
    private List<Shape> shapes = new ArrayList<Shape>();

    @Override
    public void draw(String fillColor) {
        for(Shape sh : shapes)
        {
            sh.draw(fillColor);
        }
    }

    //색칠 할 도형 추가
    public void add(Shape s){
        this.shapes.add(s);
    }

    //색칠 할 도형 삭제
    public void remove(Shape s){
        shapes.remove(s);
    }

    //색칠 할 도형 전부 삭제
    public void clear(){
        System.out.println("Clearing all the shapes from drawing");
        this.shapes.clear();
    }
}
```

**다이어그램**

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/composite.png?raw=true)



## 구성패턴을 이용하지 않으면?

만약 구성패턴을 사용하지 않으면 다음과 같은 모습의 클래스를 갖습니다.

```java
public class Drawing{

    Circle circle = new Cicle();
		Triangle triangle = new triangle();

    public void draw(String fillColor) {
			circle.draw(fillColor);
      triangle.draw(fillColor);
    }
}
```

여기서 **사각형**을 추가해보겠습니다.

```java
public class Drawing{

    Circle circle = new Cicle();
		Triangle triangle = new triangle();
		Rectangle	rectangle = new Rectangle(); // 추가
  
    public void draw(String fillColor) {
			circle.draw(fillColor);
      triangle.draw(fillColor);
      rectangle.draw(fillColor); // 추가
    }
}
```

SOLID 규칙중에 O 규칙인 <u>OCP 규칙</u>이 있습니다. 이 방법은 <u>OCP 규칙을 깨는</u> 사례에 해당됩니다.

> OCP(Open-Closed-Principle)
>
> 소프트웨어 개발 작업에 이용된 많은 모듈 중에 하나에 수정을 가할 때 그 모듈을 이용하는 다른 모듈을 줄줄이 고쳐야 한다면, 이와 같은 프로그램은 수정하기가 어렵다. 개방-폐쇄 원칙은 시스템의 구조를 올바르게 재조직(리팩토링)하여 나중에 이와 같은 유형의 변경이 더 이상의 수정을 유발하지 않도록 하는 것이다. 개방-폐쇄 원칙이 잘 적용되면, 기능을 추가하거나 변경해야 할 때 이미 제대로 동작하고 있던 원래 코드를 변경하지 않아도, 기존의 코드에 새로운 코드를 추가함으로써 기능의 추가나 변경이 가능하다.

## 테스트

**TestCompositePattern.java**

```java
public class TestCompositePattern {

    public static void main(String[] args) {
        Shape tri = new Triangle();
        Shape tri1 = new Triangle();
        Shape cir = new Circle();

        Drawing drawing = new Drawing();
        drawing.add(tri1);
        drawing.add(tri1);
        drawing.add(cir);
				// 모든 도형을 빨간 색으로 색칠
        drawing.draw("Red");

      	//모든 도형의 색을 지움.
        drawing.clear();

        drawing.add(tri);
        drawing.add(cir);
      	//모든 도형을 초록색으로 색칠 
        drawing.draw("Green");
    }

}
```

**결과**

```
Drawing Triangle with color Red
Drawing Triangle with color Red
Drawing Circle with color Red
Clearing all the shapes from drawing
Drawing Triangle with color Green
Drawing Circle with color Green
```



## 구성 패턴의 중요한 포인트

- 구성 패턴은 객체의 그룹이 하나의 객체처럼 행동할 때만 적용 됩니다. -> Shape는 draw()라는 공통 메소드가 있음
- 구성 패턴을 사용하여 트리와 같은 구조를 만들 수 있습니다.

