package com.filegenerator.format;

import java.util.ArrayList;
import java.util.List;

public class DataRowFormat implements IDataRowFormat {

    DataFieldFormat[] fields;

    public DataRowFormat(DataFieldFormat[] fields){
        this.fields = fields;
    }


    public String format(Object ... values){
        StringBuilder sb  = new StringBuilder();
        for(int i=0; i< values.length; i++){
            Object value = values[i];
            String formattedValue = value.toString();
            if(fields != null) {
                DataFieldFormat field = fields[i];
                formattedValue = field.format(value);
            }
            sb.append(formattedValue);
        }
        return sb.toString();
    }

    @Override
    public void format(StringBuilder sb, Object... values) {
            sb.append(format(values));
    }

}
