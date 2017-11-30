package com.filegenerator.format;

import org.apache.commons.csv.CSVFormat;

import java.util.ArrayList;
import java.util.List;

public class CSVDataRowFormat implements IDataRowFormat {

    CSVFormat formatter = CSVFormat.DEFAULT;

    DataFieldFormat[] fields;

    public CSVDataRowFormat(){

    }

    public CSVDataRowFormat(CSVFormat formatter){
        this.formatter = formatter;
    }

    private Object[] formatFields(Object[] values){
        List<String> formatted = new ArrayList<String>();

        for(int i=0; i< values.length; i++){
            Object value = values[i];
            String formattedValue = value.toString();
            if(fields != null) {
                DataFieldFormat field = fields[i];
                formattedValue = field.format(value);
            }
            formatted.add(formattedValue);
        }
        return formatted.toArray();
    }

    @Override
    public String format(Object... values) {
        StringBuilder sb  = new StringBuilder();
        if(fields != null){
            return formatter.format(formatFields(values));
        }else{
            return formatter.format(values);
        }
    }

    @Override
    public void format(StringBuilder sb, Object... values) {
        sb.append(format(values));
    }

    public void applyFormatter(CSVFormat formatter){
        this.formatter = formatter;
    }
}
