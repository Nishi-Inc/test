package com.nishionline.test.dynamicFactorial;

import java.util.Scanner;

import static java.lang.String.format;

/**
 * @author shuklaalok7
 * @since 28/4/15 11:57
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
            System.out.println(format("The factorial of %d is %s.", number, calculator.factorial(number)));
            System.out.println(format("Calculated factorial(%d) in %d ms.", number, System.currentTimeMillis() - startTime));
            System.out.print("\nEnter another number: ");
            number = inputScanner.nextInt();
        } while(number >=0);
    }

}
