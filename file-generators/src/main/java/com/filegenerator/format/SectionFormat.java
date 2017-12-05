package com.filegenerator.format;

import java.util.List;
import java.util.Map;

public class SectionFormat {

    String name;

    RecordFormat headFormat;
    Map<String,RecordFormat> details;
    RecordFormat tailFormat;

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


    public String format(List<Object> headers, List<Object[]> details, List<Object> tailers){
        StringBuilder sb = new StringBuilder();
        format(headers, details, tailers);
        return sb.toString();
    }


}
