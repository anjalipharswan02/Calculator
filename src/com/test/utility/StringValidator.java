package com.test.utility;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class StringValidator {
	
	public static boolean isContainsValidOperator(String expression) {
        String REGEX = "[\\+\\-\\*\\/()[0-9]*\\.[0-9]*]+";
        boolean matches=false;
		Pattern pattern = Pattern.compile(REGEX);
         Matcher matcher = pattern.matcher(expression);
         matches = matcher.matches();     
		return matches;	
	}
	
    public static boolean isValidParanthesis(String s) {
        Stack<Character> st = new Stack<>();
        HashMap<Character,Character> hm = new HashMap<>();

        hm.put(')','(');

        boolean ch=true;
        int i=0;
        for(;i<s.length();i++)
        {
            char c = s.charAt(i);

            if(c != '(' && c!= ')')
                ;
            else if(c == '(')
                st.push(c);
            else if( (c==')') && !st.isEmpty() && st.peek()==hm.get(c))
                st.pop();
            else
            {
                break;
            }
        }
        if(!st.isEmpty() || i != s.length())
            return false;
        return true;
    }
}
