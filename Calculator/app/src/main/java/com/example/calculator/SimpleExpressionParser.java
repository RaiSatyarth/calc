package com.example.calculator;

import java.util.Stack;

public class SimpleExpressionParser {

    public static double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                i--; // Move back one position

                numbers.push(Double.parseDouble(numBuilder.toString()));
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    applyOperation(numbers, operators.pop());
                }
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            applyOperation(numbers, operators.pop());
        }

        return numbers.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op2 != '+' && op2 != '-') || (op1 == '*' || op1 == '/');
    }

    private static void applyOperation(Stack<Double> numbers, char operator) {
        double b = numbers.pop();
        double a = numbers.pop();

        switch (operator) {
            case '+':
                numbers.push(a + b);
                break;
            case '-':
                numbers.push(a - b);
                break;
            case '*':
                numbers.push(a * b);
                break;
            case '/':
                if (b != 0) {
                    numbers.push(a / b);
                } else {
                    throw new ArithmeticException("Division by zero");
                }
                break;
        }
    }
}
