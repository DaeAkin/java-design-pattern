# Visitor 패턴

Visitor 패턴은 비슷한 객체의 그룹에서 어떤 동작을 해야할 때 사용됩니다. With the help of visitor pattern, we can move the operational logic from the objects to another class.

예를 들어 각각 다른 아이템들을 담는 장바구니 있다고 생각하면, 상품페이지에서 담기 버튼을 누르면 지불해야 할 총 금액이 계산이 됩니다. 우리는 아이템 클래스들을 계산하는 로직을 만들고 이 로직을 visitor 패턴을 사용해 다른 클래스로 옮겨 보겠습니다. 

## Visitor 패턴 사용해보기

visitor 패턴을 구현하기 위해선, 먼저 장바구니에 쓰여질 아이템의 각기다른 타입을을 만들어야 합니다.

**ItemElement.java**

코드~

accept 함수는 Visitor 클래스를 인자로 받습니다. 

ㅁ