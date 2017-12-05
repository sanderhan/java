package com.filegenerator.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class NACHACompanyBatch {

    public static enum SEC {
        ACK,ADV,ARC,ATX,BOC,CCD,CIE,COR, CTX,DNE,ENR,IAT,MTE,POS,PPD,POP,RCK,SHR,TEL,TRC,TRX,WEB,XCK
    }



    int serviceClassCode;
    String sec;

    String companyID;
    String companyName;
    String CompanyDiscData;
    String companyEntryDescription;
    Date compnayDescrptiveDate;
    Date effectiveDate;
    String originatorStatusCode = "";
    String originDFI;
    int batchNumber;

    List<NACHAEntry> entries = new ArrayList<NACHAEntry>();

    public Object[] toBatchHeaderArray(){
        return new Object[]{
                Integer.valueOf(serviceClassCode),
                companyName,
                CompanyDiscData,
                companyID,
                sec,
                companyEntryDescription,
                compnayDescrptiveDate,
                effectiveDate,
                "",
                originatorStatusCode,
                originDFI,
                Integer.valueOf(batchNumber)
        };
    }

}
