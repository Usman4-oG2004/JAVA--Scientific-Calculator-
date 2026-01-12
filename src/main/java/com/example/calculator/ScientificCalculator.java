package com.example.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private CalculatorEngine calculator;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isScientific = false;
    private String scientificFunc = "";

    public ScientificCalculator() {
        calculator = new CalculatorEngine();

        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "log",
            "√", "x²", "π", "AC"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.matches("[0-9]|\\.")) {
                display.setText(display.getText() + command);
            } else if (command.equals("AC")) {
                display.setText("");
                firstNumber = 0;
                operator = "";
                isScientific = false;
                scientificFunc = "";
            } else if (command.equals("=")) {
                if (isScientific) {
                    double value = Double.parseDouble(display.getText().isEmpty() ? "0" : display.getText());
                    double result = calculator.performScientificOperation(value, scientificFunc);
                    display.setText(String.valueOf(result));
                    isScientific = false;
                    scientificFunc = "";
                } else if (!operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(display.getText().isEmpty() ? "0" : display.getText());
                    double result = calculator.performOperation(firstNumber, secondNumber, operator);
                    display.setText(String.valueOf(result));
                    operator = "";
                }
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (!display.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText("");
                    isScientific = false;
                }
            } else if (command.equals("sin") || command.equals("cos") || command.equals("tan") || command.equals("log") || command.equals("√") || command.equals("x²")) {
                if (!display.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(display.getText());
                    scientificFunc = command.equals("√") ? "sqrt" : command.equals("x²") ? "pow" : command;
                    isScientific = true;
                    display.setText("");
                }
            } else if (command.equals("π")) {
                display.setText(String.valueOf(Math.PI));
            }
        } catch (NumberFormatException ex) {
            display.setText("Invalid Input");
        } catch (ArithmeticException ex) {
            display.setText("Error: Division by zero is not allowed");
        } catch (IllegalArgumentException ex) {
            display.setText("Error: " + ex.getMessage());
        } catch (Exception ex) {
            display.setText("Unexpected Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScientificCalculator());
    }
}