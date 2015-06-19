package com.nishionline.test.invoiceNumber;

import com.nishionline.test.invoiceNumber.enums.DocumentType;
import com.nishionline.test.utils.GlobalConstants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        StringBuilder numberBuilder = new StringBuilder(this.documentType.getCode().substring(0, 3).toUpperCase());
        numberBuilder.append(LocalDate.now().format(DateTimeFormatter.ofPattern(GlobalConstants.DATE_FORMAT_STRING)));
        numberBuilder.append(format("%03d", this.serial));
        numberBuilder.append(this.calculateCheckDigit(numberBuilder.toString()));

        return numberBuilder.toString();
    }


}
