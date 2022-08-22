package com.company;
import java.util.ArrayDeque;
import java.util.Deque;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ReversePolishNotationCalculatorTest {
    ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
    @Test
    public void shouldCalculateAddition() {
        String task = "4 3 +";
        assertEquals(7, calculator.calculatePolishNotation(task), "Неправильный результат сложения!");
        String task1 = "4 3 -";
        assertEquals(1, calculator.calculatePolishNotation(task1), "Неправильный результат вычитания!");
        String task2 = "4 3 *";
        assertEquals(12, calculator.calculatePolishNotation(task2), "Неправильный результат умножения!");
        String task3 = "4  3 *";
        assertEquals(12, calculator.calculatePolishNotation(task3), "Неправильно обработан пробел!");
        String task4 = "4 -3 *";
        assertEquals(-12, calculator.calculatePolishNotation(task4), "Неправильно обработано отрицательное число!");
    }


}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}




