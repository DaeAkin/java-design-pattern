# 플라이웨이트(flyweight) 패턴

## 1. 플라이웨이트 패턴이란

GoF에 따르면 <u>플라이웨이트 패턴</u>은 <u>**"공유를 사용하여 많은 수의 객체를 효율적으로 지원"**</u> 의도를 갖고 있습니다.

플라이웨이트 패턴은 어댑터 패턴과 마찬가지로 코드의 구조를 위한 디자인 패턴입니다.

플라이웨이트 패턴은 **클래스의 객체를 많이** 만들어야 할 때 사용합니다. 객체를 만들면, 객체마다 메모리 공간을 차지하기 때문에 모바일 디바이스나, 임베디드 시스템 같은 경우 메모리가 부족해질 수 있습니다. 이럴 때 플라이웨이트 패턴을 사용하면 객체를 공유하기 때문에 메모리 사용을 줄일 수 있습니다.

플라이웨이트 패턴을 적용하기 전에, 다음과 같은 사항을 점검해야 합니다.

- 애플리케이션이 만드는 **객체의 수**가 엄청 많다.
- 객채 생성이 **메모리**에게 부담이되고 오래걸린다.
- 객체 속성이 **intrinsic** 과 **extrinsic** 속성으로 나누어져야 합니다. **extrinsic** 속성은 <u>클라이언트에 의해</u> 정해질 수 있습니다.

플라이웨이트 패턴을 적용하려면 먼저 객체가 **intrinsic** 과 **extrinsic** 으로 나누어져야 합니다. intrinsic 속성은 객체를 유니크하게 만들어주고, **반면에** extrinsic 속성은 클라이언트 코드에 의해 정해지고 다른 동작을 수행하기 위해 사용됩니다. 예를 들면 Circle 객체는 **Color**나 **Width** 같은 속성이 **extrinsic** 속성 입니다.

플라이웨이트 패턴을 적용하기 위해 공유 객체를 반환하는 플라이웨이트 팩토리 클래스를 만들어야 합니다. 이번 예제에서는 선(Line) 과 타원(Oval)을 그리는 예제를 만들고,  Shape 인터페이스를 만들어 Line클래스와 Oval 클래스가 구현하도록 했습니다. 

- Line 클래스는 intrinsic 속성이 없고
- Oval 클래스는 intrinsic 속성이 있습니다.



## 2. 플라이웨이트 예제

**Shape 인터페이스**

```java
import java.awt.*;

public interface Shape {
    public void draw(Graphics g, int x, int y, int width, int height,
                     Color color);
}

```

**Line 클래스**

```java
public class Line implements Shape {

    public Line(){
        System.out.println("선 만들기");
        // 딜레이 2초
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics line, int x1, int y1, int x2, int y2,
                     Color color) {
        line.setColor(color);
        line.drawLine(x1, y1, x2, y2);
    }

}
```

**Oval 클래스**

```java
import java.awt.Color;
import java.awt.Graphics;

public class Oval implements Shape {

    //intrinsic 속성
    private boolean fill;

    public Oval(boolean f){
        this.fill=f;
        System.out.println("타원 만들기 , 색="+f);
        //딜레이 2초
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics circle, int x, int y, int width, int height,
                     Color color) {
        circle.setColor(color);
        circle.drawOval(x, y, width, height);
        if(fill){
            circle.fillOval(x, y, width, height);
        }
    }

}
```

객체가 생성되는 시간이 오래걸리는 걸 테스트하기 위해 Line 클래스와 Oval 클래스를 생성할 때 **2초**동안 지연을 시켰습니다.

## 3. Flyweight Factory

플라이웨이트 팩토리는 클라이언트에서 **객체를 만들 때** 사용됩니다.  Map을 이용해서 객체를 담아놓으며 클라이언트에서 직접 접근하지 못하게  **private로** 선언했습니다.

클라이언트가 객체의 **인스턴스를 요청**을 하게되면  Map 객체에 담겨놨던 객체를 리턴해줍니다. 만약 Map에서 **<u>해당 객체가 없으면</u>** 클래스를 만들어 Map에 담은 후 만든 객체를 리턴해줍니다.

**ShapeFactory 클래스**

```java
import java.util.HashMap;

public class ShapeFactory {

    private static final HashMap<ShapeType,Shape> shapes = new HashMap<ShapeType,Shape>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.OVAL_FILL)) {
                shapeImpl = new Oval(true);
            } else if (type.equals(ShapeType.OVAL_NOFILL)) {
                shapeImpl = new Oval(false);
            } else if (type.equals(ShapeType.LINE)) {
                shapeImpl = new Line();
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }

    public static enum ShapeType{
        OVAL_FILL,OVAL_NOFILL,LINE;
    }
}
```



## 4. 클라이언트 코드

``` java
import com.donghyeon.designpattern.flyweight.ShapeFactory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingClient extends JFrame{


    private final int WIDTH;
    private final int HEIGHT;

    private static final ShapeType[] shapes = { ShapeType.LINE, 							 ShapeType.OVAL_FILL,ShapeType.OVAL_NOFILL };
    private static final Color[] colors = { Color.RED, Color.GREEN, Color.YELLOW };

    public DrawingClient(int width, int height){
        this.WIDTH=width;
        this.HEIGHT=height;
        Container contentPane = getContentPane();

        JButton startButton = new JButton("그리기");
        final JPanel panel = new JPanel();

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(startButton, BorderLayout.SOUTH);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Graphics g = panel.getGraphics();
                for (int i = 0; i < 20; ++i) {
                    Shape shape = ShapeFactory.getShape(getRandomShape());
                    shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(),
                            getRandomHeight(), getRandomColor());
                }
            }
        });
    }

    private ShapeType getRandomShape() {
        return shapes[(int) (Math.random() * shapes.length)];
    }

    private int getRandomX() {
        return (int) (Math.random() * WIDTH);
    }

    private int getRandomY() {
        return (int) (Math.random() * HEIGHT);
    }

    private int getRandomWidth() {
        return (int) (Math.random() * (WIDTH / 10));
    }

    private int getRandomHeight() {
        return (int) (Math.random() * (HEIGHT / 10));
    }

    private Color getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    public static void main(String[] args) {
        DrawingClient drawing = new DrawingClient(500,600);
    }
}

```

클라이언트 코드는 Swing을 이용합니다.



![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/draw1.png?raw=true)

클라이언트 코드를 실행 한 후 그리기 버튼을 누르면 약 **6초**뒤에 동작이 마무리 됩니다. 객체를 생성할 때 2초의 지연을 줬기 때문입니다.

**Console**

```
타원 만들기 , 색=true
타원 만들기 , 색=false
선 만들기

```

![](https://github.com/DaeAkin/java-design-pattern/blob/master/docs/draw2.png?raw=true)

그러나 여기서 다시 그리기를 눌러보면 이미 만들어진 객체를 그대로 가져다 쓰기 때문에 지연시간 없이 바로 도형을 그리는걸 알 수 있습니다. 

여기서 도형의 색깔이 **extrinsic** 속성입니다.

## 5. JDK에서 사용 예

모든 wrapper 클래스의 `valueOf()` 메소드가 사용합니다.

## 6. 플라이웨이트 패턴의 중요한 부분

- 플라이웨이트 패턴은 복잡성을 가져오게됩니다. 공유되는 객체의 수가 많아지면 메모리와 시간 사이의 교환이 있을 수 있습니다. 요구사항에 따라 현명하게 사용해야 합니다.
- 플라이웨이트 패턴 구현은 객체가 **intrinsic** 속성을 많이 가지고 있으면 팩토리 클래스 구현이 복잡해지므로 유용하지 않습니다.