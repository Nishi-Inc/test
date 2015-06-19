package com.nishionline.test.dynamicFibonacci;

import java.util.Scanner;

import static java.lang.String.format;

/**
 * @author Ashish
 * @since 19-06-2015
 */
public class Driver {
    public static void main(String[] args) {
        // args[0] should be a boolean: true for enabling logging in console
        // args[1] should be a number to calculate the factorial

        Calculator calculator = new Calculator(Boolean.parseBoolean(args[0]));
        int number = Integer.parseInt(args[1]);
        Scanner inputScanner = new Scanner(System.in);

        do {
            long startTime = System.currentTimeMillis();
            System.out.println(format("The fibonacci number at %d position is %s.", number, calculator.fibonacci(number)));
            System.out.println(format("Calculated fibonacci(%d) in %d ms.", number, System.currentTimeMillis() - startTime));
            System.out.print("\nEnter another number: ");
            number = inputScanner.nextInt();
        } while(number >=0);
    }

}
