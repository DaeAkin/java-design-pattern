# Visitor 패턴



Visitor 패턴은 비슷한 객체의 그룹에서 어떠한 동작을 수행 해야 할 때 사용됩니다. Visitor 패턴을 이용하면 수행 로직을 다른 클래스에 둘 수 있습니다.

예를 들어 각각 다른 아이템들을 담는 장바구니 있다고 생각해 봅시다. 각각 상품들은 `아이템`으로서의 동일한 역할을 하지만, 카테고리와 가격은 다를 수 있습니다.  해당 아이템을 구매하고 싶다면 담기 버튼을 눌러 장바구니로 이동하면 지불해야 할 총 금액이 계산이 됩니다. 우리는 장바구니에 담긴 아이템 클래스들의 가격 계산하는 로직을 만들고 이 로직을 visitor 패턴을 사용해 다른 클래스로 옮겨 보겠습니다. 

## Visitor 패턴 사용해보기

Visitor 패턴을 구현하기 위해선, 먼저 장바구니에 쓰여질 아이템의 각기다른 타입을을 만들어야 합니다.

**ItemElement.java**

```java
public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
```

accept 함수는 Visitor 클래스를 인자로 받습니다. 



**Book.java**

```java
public class Book implements ItemElement{

    private int price;
    private String isbnNumber;

    public Book(int price, String isbnNumber) {
        this.price = price;
        this.isbnNumber = isbnNumber;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}
```



**Fruit.java**

```java
public class Fruit implements ItemElement{

    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name) {
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}
```

두 개의 클래스 모두 ItemElement 인터페이스를 구현해야 하기 때문에 accept() 메소드를 구현했습니다.

이제 각각의 다른 아이템 타입이 있으므로 Visitor 인터페이스를 구현해 보겠습니다. 

**ShoppingCartVisitor.java**

