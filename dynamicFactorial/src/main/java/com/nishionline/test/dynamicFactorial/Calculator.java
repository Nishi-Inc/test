package com.nishionline.test.dynamicFactorial;

import com.nishionline.test.utils.FileUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * @author shuklaalok7
 * @since 28/4/15 11:10
 */
public class Calculator {
    private static final int LIMIT_OF_RECURSIVE_STACK = 100;

    private String dataFile;
    private boolean verbose;
    private Map<Integer, String> factorialMap;

    public Calculator() {
        this.dataFile = "data.xsf";
        initializeFactorialMap();
    }

    public Calculator(boolean verbose) {
        this();
        this.verbose = verbose;
    }

    private void initializeFactorialMap() {
        this.factorialMap = new HashMap<>();
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
            this.factorialMap.put(0, "1");
            this.factorialMap.put(1, "1");
        } else {
            int i = 0;
            for (String line : lines) {
                this.factorialMap.put(i, line);
                i++;
            }
        }
    }

    /**
     *
     * @param number    To compute the factorial of
     * @return The computed factorial
     */
    public String factorial(int number) {
        if(number < 0) {
            throw new IllegalArgumentException("Number should be positive integer. Found "+number);
        }

//        This case will be covered by the following if condition
//        if(number == 1 || number ==0) {
//            return this.factorialMap.get(number);
//        }

        if(this.factorialMap.containsKey(number)) {
            if(verbose) {
                System.out.println(format("Found factorial of %d.", number));
            }
            return this.factorialMap.get(number);
        }

        if(verbose) {
            System.out.println(format("Calculating factorial of %d.", number));
        }

        // This is to battle against the stackOverflow error
        while(number-this.factorialMap.size() > LIMIT_OF_RECURSIVE_STACK) {
            this.factorial(this.factorialMap.size()+100);
        }

        String result = new BigInteger(((Integer)number).toString())
                .multiply(new BigInteger(this.factorial(number-1))).toString();
        this.factorialMap.put(number, result);
        FileUtils.appendToFile(this.dataFile, result);
        return result;
    }

}
