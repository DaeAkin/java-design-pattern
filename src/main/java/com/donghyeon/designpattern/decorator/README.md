# 데코레이터(decorator) 패턴

데코레이터 패턴은 런타임시 객체의 기능을 변경하기 위해 사용 됩니다. 그와 동시에 다른 클래스의 인스턴스들은 영향을 받지 않으며, 독립적인 객체가 변경된 동작을 얻게 됩니다. 

데코레이터 패턴은 구조 디자인 패턴 중에 하나이며 구현하기 위해 구성방법과 함께 추상 클래스나 인터페이스를 이용합니다.



## 데코레이터 패턴

객체의 동작을 상속하기 위해 상속 또는 구성을 사용할 수 있지만, 컴파일 때 완료되고, 모든 클래스 인스턴스에 적용 되버립니다. 그래서 런타임 시에 동작을 삭제하거나 새로운 동작을 추가할 수 없게 됩니다. 이 때 데코레이터 패턴을 사용하면 됩니다. 

각각 다른 종류의 자동차를 구현한다고 가정해봅시다. 그러면 Car 인터페이스를 만들고, assemble 메소드를 정의한다음 Basic Car 라는 걸 만듭니다. 게다가 스포츠카나 럭셔니카로 상속을 할 수 있습니다. 구현 계층은 다음과 같습니다.