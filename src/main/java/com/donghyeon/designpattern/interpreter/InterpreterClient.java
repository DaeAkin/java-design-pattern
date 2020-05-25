package com.donghyeon.designpattern.interpreter;

public class InterpreterClient {

    public InterpreterContext ic;

    public InterpreterClient(InterpreterContext ic) {
        this.ic = ic;
    }

    public String interpret(String str) {
        Expression exp = null;
        if(str.contains("16진수")) {
            exp = new IntToHexExpression(Integer.parseInt(str.substring(0,str.indexOf(" "))));
        } else if(str.contains("2진수")) {
            exp = new IntToBinaryExpression(Integer.parseInt(str.substring(0,str.indexOf(" "))));
        } else return str;

        return exp.interpret(ic);
    }

     public static void main(String[] args) {
         String str1 = "28 의 2진수 ";
         String str2 = "28 의 16진수 ";

         InterpreterClient ec = new InterpreterClient(new InterpreterContext());
         System.out.println(str1+"= "+ec.interpret(str1));
         System.out.println(str2+"= "+ec.interpret(str2));
      }
}
