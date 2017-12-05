package com.filegenerator.common;

import lombok.Data;

@Data
public class NACHAAddenda {

    int recordType = 7;
    String addendaTypeCode = "05";
    String paymentInformation;
    int addendaSequence;
    int entrySequence;

    public Object[] toArray(){
        return new Object[] {
                addendaTypeCode,
                paymentInformation,
                Integer.valueOf(addendaSequence),
                Integer.valueOf(entrySequence)
        };
    }
}
