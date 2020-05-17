package com.donghyeon.designpattern.visitor;

public class VisitorPatternTests {
    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{new Book(20, "1234"),new Book(100, "5678"),
                new Fruit(10, 2, "바나나"), new Fruit(5, 5, "사과")};

        int total = calculatePrice(items);
        System.out.println("총 금액 = "+total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum=0;
        for(ItemElement item : items){
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}
