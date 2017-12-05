package com.filegenerator.common;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

@Data
public class NACHAEntry {
    int recordType = 6;
    int transactionCode = 27;
    String receivingDFI;
    int checkDigit;
    String DFIAccount;
    BigDecimal amount = new BigDecimal(0);
    String individialID;
    String individialName;
    String discretionaryData;
    String originDFI;
    int sequence;

    String addenda = null;
    String addendaType = "05";

    public boolean hasAddenda(){
        return addenda == null ? false: true;
    }
    public Object[] toArray(){
        return new Object[]{
                Integer.valueOf(transactionCode),
                receivingDFI,
                Integer.valueOf(checkDigit),
                DFIAccount,
                amount,
                individialID,
                individialName,
                discretionaryData,
                addenda == null ? Integer.valueOf(0) : Integer.valueOf(1),
                originDFI,
                Integer.valueOf(sequence)
        };
    }
}
