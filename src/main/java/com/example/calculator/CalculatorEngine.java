package com.example.calculator;

public class CalculatorEngine {

    public double calculate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new NumberFormatException("Invalid Input");
        }
        expression = expression.trim();

        // Handle scientific functions with parentheses
        if (expression.startsWith("sin(") && expression.endsWith(")")) {
            double angle = Double.parseDouble(expression.substring(4, expression.length() - 1));
            return performScientificOperation(Math.toRadians(angle), "sin");
        } else if (expression.startsWith("cos(") && expression.endsWith(")")) {
            double angle = Double.parseDouble(expression.substring(4, expression.length() - 1));
            return performScientificOperation(Math.toRadians(angle), "cos");
        } else if (expression.startsWith("tan(") && expression.endsWith(")")) {
            double angle = Double.parseDouble(expression.substring(4, expression.length() - 1));
            return performScientificOperation(Math.toRadians(angle), "tan");
        } else if (expression.startsWith("log(") && expression.endsWith(")")) {
            double value = Double.parseDouble(expression.substring(4, expression.length() - 1));
            return performScientificOperation(value, "log");
        } else if (expression.endsWith(" √")) {
            double value = Double.parseDouble(expression.substring(0, expression.length() - 2));
            return performScientificOperation(value, "sqrt");
        } else if (expression.endsWith(" x²")) {
            double value = Double.parseDouble(expression.substring(0, expression.length() - 3));
            return performScientificOperation(value, "pow");
        } else {
            // Basic arithmetic: assume format "a op b"
            String[] parts = expression.split(" ");
            if (parts.length != 3) {
                throw new NumberFormatException("Invalid Input");
            }
            double a = Double.parseDouble(parts[0]);
            String op = parts[1];
            double b = Double.parseDouble(parts[2]);
            return performOperation(a, b, op);
        }
    }

    public double performOperation(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    public double performScientificOperation(double value, String func) {
        switch (func) {
            case "sin":
                return Math.sin(value);
            case "cos":
                return Math.cos(value);
            case "tan":
                return Math.tan(value);
            case "log":
                if (value <= 0) {
                    throw new IllegalArgumentException("Logarithm of non-positive number");
                }
                return Math.log(value);
            case "sqrt":
                if (value < 0) {
                    throw new IllegalArgumentException("Square root of negative number");
                }
                return Math.sqrt(value);
            case "pow":
                return value * value; // square
            default:
                throw new IllegalArgumentException("Unknown function");
        }
    }
}