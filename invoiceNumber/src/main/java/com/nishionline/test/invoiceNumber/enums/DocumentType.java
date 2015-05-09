package com.nishionline.test.invoiceNumber.enums;

/**
 * @author shuklaalok7
 * @since 10/5/15 04:04
 */
public enum DocumentType {
    ESTIMATE("EST"),
    INVOICE("INV");

    private String code;

    DocumentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
