package com.donghyeon.designpattern.templatemethod;

public class TemplateMethodTests {
     public static void main(String[] args) {
         WoodenHouse woodenHouse = new WoodenHouse();
         woodenHouse.buildHouse();
         woodenHouse.buildWalls();

         System.out.println("************");

         GlassHouse glassHouse = new GlassHouse();
        glassHouse.buildHouse();
      }
}
