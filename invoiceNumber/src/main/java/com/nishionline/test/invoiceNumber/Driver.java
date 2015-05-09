package com.nishionline.test.invoiceNumber;

import com.nishionline.test.invoiceNumber.enums.DocumentType;

import java.util.Scanner;

/**
 * @author shuklaalok7
 * @since 10/5/15 03:47
 */
public class Driver {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        boolean repeat = false;

        do {
            System.out.println("Choose an option-\n1. Generate unique number\n2. Verify number");

            try {
                switch (inputScanner.nextInt()) {
                    case 1:
                        repeat = false;
                        System.out.println("Enter the document type-");
                        for(DocumentType documentType : DocumentType.values()) {
                            System.out.println(documentType.name());
                        }
                        DocumentType documentType = DocumentType.valueOf(inputScanner.next());
                        System.out.print("Enter the serial: ");
                        System.out.println(new NumberGenerator(documentType, inputScanner.nextInt()).generate());
                        break;

                    case 2:
                        repeat = false;
                        System.out.print("Enter the number to verify: ");
                        System.out.println(new NumberVerifier().verify(inputScanner.next()));
                        break;

                    default:
                        System.out.println("Please select a correct option.");
                        repeat = true;
                        break;
                }
            } catch (Exception e) {
                inputScanner.next();
                repeat = true;
            }
        } while (repeat);
    }


}
