package com.filegenerator.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NACHABatchControl {
    int recordType = 8;
    int serviceClassCode;
    int entryCount;
    BigDecimal totalDebits = new BigDecimal(0);
    BigDecimal totalCredits = new BigDecimal(0);
    String companyID;
    String messageAuthenticationCode;
    String originDFI;
    int batchNumber;

    public Object[] toArray(){
        return new Object[]{
                recordType,
                serviceClassCode,
                entryCount,
                totalDebits.movePointRight(2).intValue(),
                totalCredits.movePointRight(2).intValue(),
                companyID,
                messageAuthenticationCode,
                "",
                originDFI,
                batchNumber
        };
    }

    public void setValues(Object[] values){
        recordType = Integer.valueOf(values[0].toString()).intValue();
        serviceClassCode = Integer.valueOf(values[1].toString()).intValue();
        entryCount = Integer.valueOf(values[2].toString()).intValue();
        totalDebits = new BigDecimal((long)values[3]).movePointLeft(2);
        totalCredits = new BigDecimal( (long)values[4]).movePointLeft(2);
        companyID = (String) values[5];
        messageAuthenticationCode = (String) values[6];
        // skip reserved field values[7]
        originDFI = (String) values[8];
        batchNumber = Integer.valueOf(values[9].toString()).intValue();
    }
}
