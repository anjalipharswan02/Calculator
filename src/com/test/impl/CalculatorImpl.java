package com.test.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorImpl {

    private Stack<Character> signStack;
    private Stack<Integer> priorityStackForCurlyBraces;
    private Map<Character, Integer> priorityMap;
    private Stack<Integer> numsStack;

    private int evaluateExpression(final int a, final int b, final char c) throws ArithmeticException{

        int val = 0;

        switch (c) {
            case '/':
                try {
                    val = a / b;
                }
                catch (ArithmeticException ae) {
                    throw new ArithmeticException("division by zero found");
                }
                break;
            case '*':
                val = a * b;
                break;
            case '+':
                val = a + b;
                break;
            case '-':
                val = a - b;
                break;
        }
        return val;
    }

    private void compute()  throws ArithmeticException{
        int right = numsStack.pop();
        char operator = signStack.pop();
        int left = numsStack.pop();

        int result = evaluateExpression(left, right, operator);
        numsStack.push(result);
    }

    private void computeHigherPrioExpression(final int priority) throws ArithmeticException{

        while (signStack.size() > 0) {
            char operator = (char) signStack.peek();

            if (operator != '(' && (int) priorityMap.get(operator) >= priority) {
                compute();
            }
            else {
                break;
            }
        }
    }

    public int computeResult(String expression) throws ArithmeticException{

        signStack = new Stack<>();
        numsStack = new Stack<>();
        priorityStackForCurlyBraces = new Stack<>();

        priorityMap = new HashMap<Character, Integer>();
        priorityMap.put('(', 0);
        priorityMap.put('/', 4);
        priorityMap.put('*', 3);
        priorityMap.put('+', 2);
        priorityMap.put('-', 1);
        priorityMap.put(')', 0);
        int prevPriority = -1;
        int result = 0;

        int i = 0;

        while (signStack.size() > 0 || i < expression.length()) {
            if (i >= expression.length()) {
                compute();
            }
            else if(expression.charAt(i) == '('){
                priorityStackForCurlyBraces.push(prevPriority);
                prevPriority = -1; // resetting this
                signStack.push('(');
            }
            else if(expression.charAt(i) == ')'){
                computeHigherPrioExpression(0);

                prevPriority = priorityStackForCurlyBraces.pop();
                signStack.pop(); // popping opening brace
            }
            else if (priorityMap.containsKey(expression.charAt(i))) {// is operator
                if (priorityMap.get(expression.charAt(i)) >= prevPriority) {
                    signStack.push(expression.charAt(i));
                    prevPriority = priorityMap.get(expression.charAt(i));
                } else {
                    prevPriority = priorityMap.get(expression.charAt(i));
                    computeHigherPrioExpression(prevPriority);
                    signStack.push(expression.charAt(i));
                }
            }
            else if(Character.isDigit(expression.charAt(i))) {
                int temp = expression.charAt(i) - '0';

                while( i+1 < expression.length() && Character.isDigit(expression.charAt(i+1)) ) {
                    temp *= 10;
                    temp += (expression.charAt(i+1) - '0');
                    i++;
                }

                numsStack.push(temp);
            }
            i++;
        }

        if(signStack.size() == 0)
            result = numsStack.pop();
        return result;
    }
}

