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
                recordType,
                priorityCode,
                immediateDestination,
                immedidateOrigin,
                fileCreationDateTime,
                fileCreationDateTime,
                fileIDModifier,
                recordSize,
                blockingFactor,
                formatCode,
                immediateDestinationName,
                immediateOriginName,
                referenceCode
        };
    }

    public void setValues(Object [] values)
            throws ParseException {

        recordType = Integer.valueOf(values[0].toString()).intValue();
        priorityCode = Integer.valueOf(values[1].toString()).intValue();
        immediateDestination = (String) values[2];
        immedidateOrigin = (String) values[3];
        SimpleDateFormat dfDate = new SimpleDateFormat("yyMMdd");
        SimpleDateFormat dfTime = new SimpleDateFormat("HHmm");
        SimpleDateFormat dfDateTime = new SimpleDateFormat("yyMMddHHmm");
        this.fileCreationDateTime = dfDateTime.parse(dfDate.format(values[4]) + dfTime.format(values[5]));
        fileIDModifier = (String) values[6];
        recordSize = Integer.valueOf(values[7].toString()).intValue();
        blockingFactor = Integer.valueOf(values[8].toString()).intValue();
        formatCode = Integer.valueOf(values[9].toString()).intValue();
        immediateDestinationName = (String) values[10];
        immediateOriginName = (String) values[11];
        referenceCode = (String) values[12];
    }

    public void addBatch(NACHACompanyBatch batch) {
        batches.add(batch);
    }


}
