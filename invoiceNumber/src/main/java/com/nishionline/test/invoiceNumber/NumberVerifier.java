package com.nishionline.test.invoiceNumber;

import com.nishionline.test.utils.GlobalConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Verifies a number
 *
 * @author shuklaalok7
 * @since 10/5/15 04:02
 */
public class NumberVerifier extends DocumentNumberUtils {
    Pattern pattern = Pattern.compile("[A-Z]{3}(?:(?:(?:(?:(?:[13579][26]|[2468][048])00)|(?:[0-9]{2}(?:(?:[13579][26])|(?:[2468][048]|0[48]))))(?:(?:(?:09|04|06|11)(?:0[1-9]|1[0-9]|2[0-9]|30))|(?:(?:01|03|05|07|08|10|12)(?:0[1-9]|1[0-9]|2[0-9]|3[01]))|(?:02(?:0[1-9]|1[0-9]|2[0-9]))))|(?:[0-9]{4}(?:(?:(?:09|04|06|11)(?:0[1-9]|1[0-9]|2[0-9]|30))|(?:(?:01|03|05|07|08|10|12)(?:0[1-9]|1[0-9]|2[0-9]|3[01]))|(?:02(?:[01][0-9]|2[0-8])))))\\d{3}[0-9A-F]");

    public boolean verify(String number) {
        return this.pattern.matcher(number).matches()
                && this.verifyDate(number)
                && this.verifyCheckCharacter(number);
    }

    /**
     * @param number To check the number
     * @return {@code true}, if the check-number is correct
     */
    private boolean verifyCheckCharacter(String number) {
        char checkCharacter = number.charAt(number.length() - 1);
        return checkCharacter == calculateCheckDigit(number.substring(0, number.length() - 1));
    }

    /**
     * @param number The provided number to verify
     * @return {@code true}, if the date supplied is correct and falls on or after Jan 1, 2014
     */
    private boolean verifyDate(String number) {
        String dateString = number.substring(3, 11);
        DateFormat dateFormat = new SimpleDateFormat(GlobalConstants.DATE_FORMAT_STRING);
        try {
            Date date = dateFormat.parse(dateString);
            return date.compareTo(dateFormat.parse("20140101")) != -1;
        } catch (ParseException e) {
            return false;
        }
    }

}
