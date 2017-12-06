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
    String individualID;
    String individualName;
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
                recordType,
                transactionCode,
                receivingDFI,
                checkDigit,
                DFIAccount,
                amount.movePointRight(2).intValue(),
                individualID,
                individualName,
                discretionaryData,
                addenda == null ? 0 : 1,
                originDFI,
                sequence
        };
    }

    public void setValues(Object[] values){
        recordType = Integer.valueOf(values[0].toString()).intValue();
        transactionCode = Integer.valueOf(values[1].toString()).intValue();
        receivingDFI = (String) values[2];
        checkDigit = Integer.valueOf(values[3].toString()).intValue();
        DFIAccount = (String) values[4];
        amount = new BigDecimal((long)values[5]).movePointLeft(2);
        individualID = (String) values[6];
        individualName = (String) values[7];
        discretionaryData = (String) values[8];
        //addenda = (String) values[9];
        originDFI = (String) values[10];
        sequence = Integer.valueOf(values[11].toString()).intValue();
    }
}
