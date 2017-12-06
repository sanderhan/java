package com.filegenerator.format;

import com.filegenerator.InvalidFormatDefinition;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatFileFormat implements IFormat<Map<String,List<Object[]>>{

    Map<String,RecordFormat> formatterMap = new HashMap<>();


    public FlatFileFormat(Map<String,RecordFormat>  namedFormats){
        this.formatterMap = namedFormats
    }

    @Override
    public void format(StringBuilder sb, Map<String, List<Object[]>> dataSets) {
        for (Map.Entry<String, List<Object[]>> entry : dataSets.entrySet())
        {
            String dataSetName = entry.getKey();
            List dataSet = entry.getValue();
            RecordFormat rf = formatterMap.get(dataSetName);
            rf.formatDataSet(sb, dataSet);
        }
    }

    @Override
    public Map<String,List<Object[]>>  parse(InputStream in) throws InvalidFormatDefinition {
        return null;
    }
}
