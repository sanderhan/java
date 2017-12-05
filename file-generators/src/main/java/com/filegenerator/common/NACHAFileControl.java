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
    BigInteger entryHash;
    BigDecimal totalDebits;
    BigDecimal totalCredits;


    public Object[] toArray(){
        return new Object[]{
                Integer.valueOf(batchCount),
                Integer.valueOf(blockCount),
                entryHash,
                totalDebits,
                totalCredits,
                new String(),
        };
    }
}
