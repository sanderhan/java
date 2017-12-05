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
                Integer.valueOf(serviceClassCode),
                Integer.valueOf(entryCount),
                totalDebits,
                totalCredits,
                companyID,
                messageAuthenticationCode,
                "",
                originDFI,
                Integer.valueOf(batchNumber)
        };
    }

}
