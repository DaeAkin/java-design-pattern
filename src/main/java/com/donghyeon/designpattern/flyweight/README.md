# 플라이웨이트(flyweight) 패턴



GoF에 따르면 <u>플라이웨이트 패턴</u>은 **"공유를 사용하여 많은 수의 객체를 효율적으로 지원"** 의도를 갖고 있습니다.

플라이웨이트 패턴은 어댑터 패턴과 마찬가지로 코드의 구조를 위한 디자인 패턴입니다.

플라이웨이트 패턴은 **클래스의 객체를 많이** 만들어야 할 때 사용합니다. 객체를 만들면, 객체마다 메모리 공간을 차지하기 때문에 모바일 디바이스나, 임베디드 시스템 같은 경우 메모리가 부족해질 수 있습니다. 이럴 때 플라이웨이트 패턴을 사용하면 객체를 공유하기 때문에 메모리 사용을 줄일 수 있습니다.

플라이웨이트 패턴을 적용하기 전에, 다음과 같은 사항을 점검해야 합니다.

- 애플리케이션이 만드는 객체의 수가 엄청 많다.
- 객채 생성이 메모리에게 부담이되고 오래걸린다.
- The object properties can be divided into intrinsic and extrinsic properties, extrinsic properties of an Object should be defined by the client program.
- 객체 속성이 intrinsic 과 extrinsic 속성으로 나누어져야 합니다. extrinsic 속성은 클라이언트에 의해 정해질 수 있습니다.

플라이웨이트 패턴을 적용하려면 먼저 객체가 **intrinsic** 과 **extrinsic** 으로 나누어져야 합니다. intrinsic 속성은 객체를 유니크하게 만들어주고, 반면에 extrinsic 속성은 클라이언트 코드에 의해 정해지고 다른 동작을 수행하기 위해 사용됩니다. 예를 들면 Circle 객체는 **Color**나 **Width** 같은 속성이 extrinsic 속성 입니다.

플라이웨이트 패턴을 적용하기 위해 공유 객체를 반환하는 플라이웨이트 팩토리 클래스를 만들어야 합니다. 이번 예제에서는 선(Line) 과 타원(Oval)을 그리는 예제를 만들고,  Shape 인터페이스를 만들어 Line클래스와 Oval 클래스가 구현하도록 했습니다. 

- Line 클래스는 intrinsic 속성이 없고
- Oval 클래스는 intrinsic 속성이 있습니다.

