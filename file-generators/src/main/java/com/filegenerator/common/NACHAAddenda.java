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
                recordType,
                addendaTypeCode,
                paymentInformation,
                Integer.valueOf(addendaSequence),
                Integer.valueOf(entrySequence)
        };
    }

    public void setValues(Object[] values){
        recordType = (int) values[0];
        addendaTypeCode = (String) values[1];
        paymentInformation = (String) values[2];
        addendaSequence = (int) values[3];
        entrySequence = (int) values[4];
    }
}
