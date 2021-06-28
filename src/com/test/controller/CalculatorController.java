package com.test.controller;
import com.test.impl.*;
import com.test.utility.StringValidator;

import java.util.*;
import java.util.regex.Pattern;

public class CalculatorController {

    public static void main(String[] args) {
    	
    	//input expression
    	Scanner sc=new Scanner(System.in);
    	String expression=sc.next();
       
    	CalculatorImpl c = new CalculatorImpl();

            System.out.print("Input:" + expression);
       
            if(!StringValidator.isContainsValidOperator(expression)) {
                System.out.println(" Result: Invalid OPERATOR IN EXPRESSION");
                         
            }
            
            else if(!StringValidator.isValidParanthesis(expression)) {
                System.out.println(" Result: Invalid Parenthesis");
                         
            }

            else {
            	try {
            
                System.out.println(" Result:" + c.computeResult(expression));
            }
            catch (ArithmeticException ae) {
                System.out.println(" Result has below error:");
                ae.printStackTrace();
            }
     }
    }
}
