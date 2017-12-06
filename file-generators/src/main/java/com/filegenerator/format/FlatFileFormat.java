package com.filegenerator.format;

import com.filegenerator.InvalidFormatDefinition;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FlatFileFormat implements IFormat<Map<String,List<<List<Object>>>{

    String name;

    Map<String,RecordFormat> formatterMap;

    public boolean hasHead(){
        if(headFormat != null)
            return true;
        else
            return false;
    }
    public boolean hasTail(){
        if(tailFormat != null)
            return true;
        else
            return false;
    }


    public String format(List<Object> headers, List<<List<Object>> details, List<Object> tailers){
        StringBuilder sb = new StringBuilder();
        format(headers, details, tailers);
        return sb.toString();
    }


    @Override
    public void format(StringBuilder sb, Map<String, List> dataSets) {
        for (Map.Entry<String, String> entry : dataSets.entrySet())
        {
            String dataSetName = entry.getKey();
            List dataSet = entry.getValue();
            RecordFormat rf = formatterMap.get(dataSetName);
            rf.formatDataSet(sb, dataSet);
        }
    }

    @Override
    public Map<String, List<<List<Object>>  parse(InputStream in) throws InvalidFormatDefinition {
        return null;
    }
}
