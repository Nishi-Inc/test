package com.nishionline.test.invoiceNumber;

import com.nishionline.test.invoiceNumber.enums.DocumentType;

import java.util.Calendar;
import java.util.Objects;

import static java.lang.String.format;

/**
 * Generates a number
 * @author shuklaalok7
 * @since 10/5/15 04:02
 */
public class NumberGenerator extends DocumentNumberUtils {
    private DocumentType documentType;
    private int serial;

    public NumberGenerator(DocumentType documentType, int serial) {
        Objects.requireNonNull(documentType);
        assert serial != 0;

        this.documentType = documentType;
        this.serial = serial;
    }

    public String generate() {
        Calendar calendar = Calendar.getInstance();
        StringBuilder numberBuilder = new StringBuilder(this.documentType.getCode());
        numberBuilder.append(calendar.get(Calendar.YEAR));
        numberBuilder.append(format("%02d", calendar.get(Calendar.MONTH) + 1));
        numberBuilder.append(format("%02d", calendar.get(Calendar.DAY_OF_MONTH)));
        numberBuilder.append(format("%03d", this.serial));
        numberBuilder.append(this.calculateCheckDigit(numberBuilder.toString()));

        return numberBuilder.toString();
    }


}
