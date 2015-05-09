package com.nishionline.test.invoiceNumber;

import com.nishionline.test.utils.GlobalConstants;

/**
 * @author shuklaalok7
 * @since 10/5/15 04:10
 */
public abstract class DocumentNumberUtils {

    protected char calculateCheckDigit(String number) {
        String[] digits = number.split(GlobalConstants.BLANK);

        int i=0, sum = 0;
        for(String digit : digits) {
            int intValue = this.getIntValue(digit);
            sum += (i%2==0? intValue*2: intValue);
            i++;
        }

        return this.getCheckCharacter(sum % 16);
    }

    /**
     *
     * @param i    To return the char for int
     * @return The char '0' to '9' for 0-9 and 'A' to 'F' for 10 to 15
     */
    private char getCheckCharacter(int i) {
        assert i<16;

        return i < 10 ? Character.toChars('0' + i)[0] : Character.toChars('A' - 10 + i)[0];
    }

    /**
     *
     * @param digit    to calculate intValue of
     * @return the intValue 0 to 9 and for 'A' to 'Z' it returns 10 to 35
     */
    private int getIntValue(String digit) {
        int value = 0;
        try {
            value = Integer.parseInt(digit);
        } catch (NumberFormatException e) {
            value = digit.charAt(0)-'A'+10;
        }

        return value;
    }

}
