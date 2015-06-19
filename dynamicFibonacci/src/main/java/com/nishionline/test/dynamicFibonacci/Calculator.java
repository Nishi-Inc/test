package com.nishionline.test.dynamicFibonacci;

import com.nishionline.test.utils.FileUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author Ashish
 * @since 19-06-2015
 */
public class Calculator {

    private static final int LIMIT_OF_RECURSIVE_STACK = 100;

    private String dataFile;
    private boolean verbose;
    private Map<Integer, String> fibonacciMap;

    public Calculator() {
        this.dataFile = "data.fib";
        initializeFactorialMap();
    }

    public Calculator(boolean verbose) {
        this();
        this.verbose = verbose;
    }

    private void initializeFactorialMap() {
        this.fibonacciMap = new HashMap<>();
        String[] lines = null;
        try {
            String fileContent = FileUtils.readFile(dataFile);
            if (fileContent!=null) {
                lines = fileContent.split("\n");
            }
        } catch (IOException e) {
            if (verbose) {
                e.printStackTrace();
            }
        }

        if(lines == null || lines.length < 2) {
            FileUtils.appendToFile(this.dataFile, "1\n1");
            this.fibonacciMap.put(1, "1");
            this.fibonacciMap.put(2, "1");
        } else {
            int i = 0;
            for (String line : lines) {
                this.fibonacciMap.put(i, line);
                i++;
            }
        }
    }

    /**
     *
     * @param number    To compute the fibonacci at
     * @return The computed fibonacci
     */
    public String fibonacci(int number) {
        if(number < 1) {
            throw new IllegalArgumentException("Number should be positive integer. Found "+number);
        }

        if(this.fibonacciMap.containsKey(number)) {
            if(verbose) {
                System.out.println(format("Found fibonacci at %d.", number));
            }
            return this.fibonacciMap.get(number);
        }

        if(verbose) {
            System.out.println(format("Calculating fibonacci at %d.", number));
        }

        // This is to battle against the stackOverflow error
        while(number-this.fibonacciMap.size() > LIMIT_OF_RECURSIVE_STACK) {
            this.fibonacci(this.fibonacciMap.size()+100);
        }

        String result = new BigInteger(this.fibonacci(number-1)).add(new BigInteger(this.fibonacci(number - 2)))
                .toString();
        this.fibonacciMap.put(number, result);
        FileUtils.appendToFile(this.dataFile, result);
        return result;
    }


}
