package com.filegenerator.common;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class NACHAFileControl {
    int recordType = 9;
    int batchCount;
    int blockCount;
    int entryCount;
    long entryHash;
    BigDecimal totalDebits = new BigDecimal(0);
    BigDecimal totalCredits = new BigDecimal(0);


    public Object[] toArray(){
        return new Object[]{
                recordType,
                batchCount,
                blockCount,
                entryHash,
                totalDebits.multiply(new BigDecimal(100)).intValue(),
                totalCredits.multiply(new BigDecimal(100)).intValue(),
                "",
        };
    }


    public void setValues(Object[] values){
        recordType = Integer.valueOf(values[0].toString()).intValue();
        batchCount = Integer.valueOf(values[1].toString()).intValue();
        blockCount = Integer.valueOf(values[2].toString()).intValue();
        entryCount = Integer.valueOf(values[3].toString()).intValue();
        entryHash = (long) values[4];
        totalDebits = new BigDecimal( ((long)values[5])/100.00);;
        totalCredits = new BigDecimal( ((long)values[6])/100.00);;
    }
}
