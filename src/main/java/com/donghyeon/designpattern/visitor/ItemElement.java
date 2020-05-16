package com.donghyeon.designpattern.visitor;

public interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}
