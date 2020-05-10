package com.donghyeon.designpattern.visitor;

public class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
    @Override
    public int visit(Book book) {
        int cost=0;
        // book 값이 50달러 이상이면 5달러 할인
        if(book.getPrice() > 50) {
            cost = book.getPrice() - 5;
        } else{
            cost = book.getPrice();
        }
        System.out.println("책 번호 :: " + book.getIsbnNumber() + " 가격 = " + cost);

        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg()*fruit.getWeight();
        System.out.println(fruit.getName() + " 가격 = "+cost);
        return cost;
    }
}
