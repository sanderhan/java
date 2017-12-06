package com.filegenerator.common;

import lombok.Data;

import java.util.*;

@Data
public class NACHACompanyBatch {



    public static enum SEC {
        ACK,ADV,ARC,ATX,BOC,CCD,CIE,COR, CTX,DNE,ENR,IAT,MTE,POS,PPD,POP,RCK,SHR,TEL,TRC,TRX,WEB,XCK
    }


    int recordType = 5;
    int serviceClassCode;
    String sec;

    String companyID;
    String companyName;
    String CompanyDiscData;
    String companyEntryDescription;
    Date companyDescrptiveDate;
    Date effectiveDate;
    String originatorStatusCode = "";
    String originDFI;
    int batchNumber;

    List<NACHAEntry> entries = new ArrayList<NACHAEntry>();

    public Object[] toBatchHeaderArray(){
        return new Object[]{
                recordType,
                serviceClassCode,
                companyName,
                CompanyDiscData,
                companyID,
                sec,
                companyEntryDescription,
                companyDescrptiveDate,
                effectiveDate,
                "",
                originatorStatusCode,
                originDFI,
                batchNumber
        };
    }

    public void setValues(Object[] values){
        recordType = Integer.valueOf(values[0].toString()).intValue();
        serviceClassCode = Integer.valueOf(values[1].toString()).intValue();
        companyName = (String) values[2];
        CompanyDiscData = (String) values[3];
        companyID = (String) values[4];
        sec = (String) values[5];
        companyEntryDescription = (String) values[6];
        companyDescrptiveDate = (Date) values[7];
        effectiveDate = (Date) values[8];
        // skip reserved field values[9]
        originatorStatusCode = (String) values[10];
        originDFI = (String) values[11];
        batchNumber = Integer.valueOf(values[12].toString()).intValue();
    }

    public void addEntry(NACHAEntry entry) {
        entries.add(entry);
    }

    public void addAddenda(List<NACHAAddenda> addendaRecords) {
        Map<Integer, List<NACHAAddenda>> entryAddendaMap = new HashMap<Integer, List<NACHAAddenda>>();

        for(NACHAAddenda addenda : addendaRecords){
            int entrySeq = addenda.getEntrySequence();
            List<NACHAAddenda> entryAddenda = entryAddendaMap.get(entrySeq);
            if(entryAddenda == null){
                entryAddenda = new ArrayList<>();
                entryAddendaMap.put(entrySeq,entryAddenda);
            }

            entryAddenda.add(addenda);
        }

        for(NACHAEntry entry : entries){
            int entrySeq = entry.getSequence();
            List<NACHAAddenda> entryAddenda = entryAddendaMap.get(entrySeq);
            if(entryAddenda == null) continue;
            StringBuilder sb = new StringBuilder();
            for(NACHAAddenda a : entryAddenda){
                entry.setAddendaType(a.getAddendaTypeCode());
                sb.append(a.getPaymentInformation());
            }
            if(!"".equals(sb.toString())){
                entry.setAddenda(sb.toString());
            }
        }
    }

}
