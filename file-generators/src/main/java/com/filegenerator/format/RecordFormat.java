package com.filegenerator.format;

import com.filegenerator.json.FormatSpec;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Getter
public class RecordFormat {


    public static RecordFormat CSV = new RecordFormat(",","","\n",false);
    public static RecordFormat FIXED_LENGTH = new RecordFormat("","","\n",false);

    FieldFormat [] indexedFields;
    Map<String,FieldFormat> namedFields = new HashMap<String,FieldFormat>();

    CharSequence delimiter = "";
    CharSequence recordHeader = "";
    CharSequence recordTerminator = "\n";

    boolean needDoubleQouted = false;

    int columnSize;

    public RecordFormat(FormatSpec spec){
        this.delimiter = spec.getDelimiter();
        this.recordHeader = spec.getRecordHeader();
        this.recordTerminator = spec.getRecordTerminator();
        this.needDoubleQouted = spec.isQuoted();
        List<FieldFormat> fields =
                spec.getFields()
                .stream()
                .map(field -> new FieldFormat(field))
                .collect(Collectors.toList());

        this.indexedFields = fields.stream().toArray(FieldFormat[]::new);

    }

    public RecordFormat(FieldFormat [] fields){
        this.indexedFields = fields;
    }
    public RecordFormat(FieldFormat [] fields, CharSequence delimiter){
        this.indexedFields = fields;
        this.delimiter = delimiter;
    }

    public RecordFormat(FieldFormat [] fields, CharSequence delimiter, CharSequence recordHeader){
        this.indexedFields = fields;
        this.delimiter = delimiter;
        this.recordHeader = recordHeader;
    }

    public RecordFormat(FieldFormat [] fields, CharSequence delimiter, CharSequence recordHeader, CharSequence recordTerminator ){
        this.indexedFields = fields;
        this.delimiter = delimiter;
        this.recordHeader = recordHeader;
        this.recordTerminator = recordTerminator;
    }

    public RecordFormat(CharSequence delimiter, CharSequence recordHeader,CharSequence recordTerminator, boolean quoted){
        this.delimiter = delimiter;
        this.recordHeader = recordHeader;
        this.recordTerminator = recordTerminator;
        this.needDoubleQouted = quoted;
    }

    public RecordFormat withFields(FieldFormat[] fields){
        return new RecordFormat(fields, this.delimiter, this.recordHeader, this.recordTerminator);
    }

    public RecordFormat withNamedFields(FieldFormat[] fields){
        return new RecordFormat(fields, this.delimiter, this.recordHeader, this.recordTerminator);
    }



    // Below are format implementations

    public String format(Object ... values){
        StringJoiner sj = new StringJoiner(delimiter,recordHeader,recordTerminator);
        for(int i=0; i< values.length; i++){
            Object value = values[i];
            String formattedValue = value.toString();
            if(indexedFields != null) {
                FieldFormat field = indexedFields[i];
                formattedValue = field.format(value);
            }
            if(needDoubleQouted){
                formattedValue = String.format("\"%s\"",formattedValue);
            }
            sj.add(formattedValue);
        }
        return sj.toString();
    }

    public String format(List<Object> values){
        return format(values.toArray());
    }

    public void format(StringBuilder sb, Object... values) {
        sb.append(format(values));
    }

    public void format(StringBuilder sb, List<Object> values) {
        format(sb,values.toArray());
    }

    public void format(StringBuilder sb, ResultSet rs)
        throws SQLException {

        int numOfCol = rs.getMetaData().getColumnCount();

        while (rs.next()) {
            List<Object> values = new ArrayList<Object>();
            for(int i=0; i< numOfCol; i++){
                values.add(rs.getObject(i));
            }
            format(sb,values);
        }
    }


    public List<Object> parse(String line){
            return null;
    }


    public void formatDataSet(StringBuilder sb, List<List<Object>> dataset){

        for(List<Object> row : dataset){
            format(sb, row);
        }
    }

    private FieldFormat findFormatByType(Object value){
        return null;
    }

    private FieldFormat findFormatByIndex(int index){
        return null;
    }

    private FieldFormat findFormatByName(String name){
        return null;
    }

}
