package com.filegenerator.format;

import java.util.List;

public class DataSetFormat implements IDataSetFormat {

    IDataRowFormat recordFormatter;

    public DataSetFormat(IDataRowFormat recordFormatter){
        this.recordFormatter = recordFormatter;
    }

    @Override
    public String format(List<List<Object>> dataset) {
        return format(dataset, null);
    }

    @Override
    public String format(List<List<Object>> dataset, List<String> header) {

        StringBuilder sb = new StringBuilder();
        if(header != null){
            recordFormatter.format(sb, header.toArray());
        }

        for(List<Object> row : dataset){
            recordFormatter.format(sb, row.toArray());
        }
        return sb.toString();
    }

    @Override
    public void format(StringBuilder sb, List<List<Object>> dataset) {
        sb.append(format(dataset));
    }

    @Override
    public void format(StringBuilder sb, List<List<Object>> dataset, List<String> header) {
        sb.append(format(dataset, header));
    }
}
