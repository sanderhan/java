package com.filegenerator.common;

import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class NACHAFile {

    int recordType = 1;
    int priorityCode;
    String immediateDestination;
    String immedidateOrigin;
    Date fileCreationDateTime;
    String fileIDModifier;
    int recordSize = 94;
    int blockingFactor = 10;
    int formatCode = 1;
    String immediateDestinationName =" ";
    String immediateOriginName =" ";
    String referenceCode=" ";

    List<NACHACompanyBatch> batches = new ArrayList<>();

    public Object[] toFileHeaderArray(){
        return new Object[]{
                Integer.valueOf(priorityCode),
                immediateDestination,
                immedidateOrigin,
                fileCreationDateTime,
                fileCreationDateTime,
                fileIDModifier,
                Integer.valueOf(recordSize),
                Integer.valueOf(blockingFactor),
                Integer.valueOf(formatCode),
                immediateDestinationName,
                immediateOriginName,
                referenceCode
        };
    }

    public void fromFileHeaderArray(Object [] values) throws ParseException {
        this.priorityCode = Integer.valueOf((String) values[1]);
        this.immediateDestination = (String) values[2];
        this.immedidateOrigin = (String) values[3];
        SimpleDateFormat dfDate = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat dfTime = new SimpleDateFormat("HHmm");
        SimpleDateFormat dfDateTime = new SimpleDateFormat("yyMMddHHmm");
        this.fileCreationDateTime = dfDateTime.parse(dfDate.format(values[4]) + dfTime.format(values[5]));
        this.fileIDModifier = (String) values[6];
        this.recordSize = (int) values[7];
        this.blockingFactor = (int) values[8];
        this.formatCode = (int) values[9];
        this.immediateDestinationName = (String) values[10];
        this.immediateOriginName = (String) values[11];
        this.referenceCode = (String) values[12];
    }

}
