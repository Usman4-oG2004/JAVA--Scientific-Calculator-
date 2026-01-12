package com.example.calculator;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorEngineTest {
    private CalculatorEngine calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalculatorEngine();
    }

    @Test
    public void testAddition() {
        assertEquals(5, calculator.calculate("2 + 3"));
        assertEquals(-1, calculator.calculate("2 + -3"));
    }

    @Test
    public void testSubtraction() {
        assertEquals(-1, calculator.calculate("2 - 3"));
        assertEquals(5, calculator.calculate("2 - -3"));
    }

    @Test
    public void testMultiplication() {
        assertEquals(6, calculator.calculate("2 * 3"));
        assertEquals(-6, calculator.calculate("2 * -3"));
    }

    @Test
    public void testDivision() {
        assertEquals(2, calculator.calculate("6 / 3"));
        assertThrows(ArithmeticException.class, () -> calculator.calculate("1 / 0"));
    }

    @Test
    public void testSquareRoot() {
        assertEquals(3, calculator.calculate("9 √"));
        assertThrows(NumberFormatException.class, () -> calculator.calculate("√ -9"));
    }

    @Test
    public void testScientificFunctions() {
        assertEquals(Math.sin(Math.PI / 2), calculator.calculate("sin(90)"));
        assertEquals(Math.cos(0), calculator.calculate("cos(0)"));
        assertEquals(Math.tan(Math.PI / 4), calculator.calculate("tan(45)"));
        assertEquals(Math.log(1), calculator.calculate("log(1)"));
    }

    @Test
    public void testInvalidInput() {
        assertThrows(NumberFormatException.class, () -> calculator.calculate("abc"));
        assertThrows(NumberFormatException.class, () -> calculator.calculate(""));
    }

    @Test
    public void testPerformOperationBasicArithmetic() {
    double eps = 1e-9;
    assertEquals(5.0, calculator.performOperation(2, 3, "+"), eps);
    assertEquals(-1.0, calculator.performOperation(2, 3, "-"), eps);
    assertEquals(6.0, calculator.performOperation(2, 3, "*"), eps);
    assertEquals(2.0, calculator.performOperation(6, 3, "/"), eps);
}

@Test
public void testPerformOperationDivisionByZeroAndInvalidOperator() {
    assertThrows(ArithmeticException.class, () -> calculator.performOperation(1, 0, "/"));
    assertThrows(IllegalArgumentException.class, () -> calculator.performOperation(1, 2, "%"));
}

@Test
public void testPerformScientificTrigonometricAndLogSqrtPow() {
    double eps = 1e-9;
    assertEquals(Math.sin(Math.PI / 2), calculator.performScientificOperation(Math.PI / 2, "sin"), eps);
    assertEquals(Math.cos(0), calculator.performScientificOperation(0, "cos"), eps);
    assertEquals(Math.tan(Math.PI / 4), calculator.performScientificOperation(Math.PI / 4, "tan"), eps);
    assertEquals(Math.log(1), calculator.performScientificOperation(1, "log"), eps);
    assertEquals(3.0, calculator.performScientificOperation(9, "sqrt"), eps);
    assertEquals(16.0, calculator.performScientificOperation(4, "pow"), eps); // pow -> square
}

@Test
public void testPerformScientificInvalidInputsAndFunctions() {
    assertThrows(IllegalArgumentException.class, () -> calculator.performScientificOperation(0, "log"));
    assertThrows(IllegalArgumentException.class, () -> calculator.performScientificOperation(-1, "log"));
    assertThrows(IllegalArgumentException.class, () -> calculator.performScientificOperation(-9, "sqrt"));
    assertThrows(IllegalArgumentException.class, () -> calculator.performScientificOperation(2, "unknownFunc"));
}
}