package com.mikalai.turbo.brackets;

import java.util.Deque;
import java.util.LinkedList;

public class BracketsChecker {

    public static void main(String[] args) {
        String validCombo = "{ [ ( { } [ ] ) ] }";
        String invalidCombo = "{ ( [ ] ] }";

        System.out.println(test(validCombo));
        System.out.println(test(invalidCombo));
    }

    private static boolean test(String test) {
        Deque<Character> stack = new LinkedList<>();


        for (int i = 0; i < test.length();i++){
            char ch = test.charAt(i);
            if (ch == '{' || ch == '(' || ch == '['){
                stack.push(ch);
            }

            if (ch == '}' || ch == ')' || ch == ']'){
                if (stack.isEmpty())
                    return false;

                char lastChar =  stack.pop();
                if (!(ch == '}' && lastChar == '{' || ch == ')' && lastChar == '(' || ch == ']' && lastChar == '[' )){
                    return false;
                }
            }
        }


        System.out.println(a)
        return stack.isEmpty();



    }
}
