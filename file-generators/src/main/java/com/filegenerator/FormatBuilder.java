package com.filegenerator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.format.FlatFileFormat;
import com.filegenerator.format.IFormat;
import com.filegenerator.format.NACHAFormat;
import com.filegenerator.format.RecordFormat;
import com.filegenerator.json.FormatSpec;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FormatBuilder {


    private Map<String, RecordFormat> formatMap = new HashMap<>();
    private String type = "FLAT";
    private static Map<String, FormatBuilder> builderMap = new  HashMap<>();

    private FormatBuilder(String type){
        this.type = type;
    }

    public static FormatBuilder getInstance(String type){
        if(builderMap.containsKey(type)){
            return builderMap.get(type);
        }else{
            FormatBuilder newBuilder = new FormatBuilder(type);
            builderMap.put(type, newBuilder);
            return newBuilder;
        }
    }

    public FormatBuilder setFormats( Map<String, RecordFormat> formatMap){
        this.formatMap = formatMap;
        return this;
    }

    public FormatBuilder addFormat( String name, RecordFormat format){
        formatMap.put(name, format);
        return this;
    }


    public FormatBuilder importJsonFormat( String name, String jsonFields)
            throws InvalidFormatDefinition {
        formatMap.put(name, buildRecordFormat(jsonFields));
        return this;
    }

    public FormatBuilder importJsonFormatFile( String name, String formatFile)
            throws InvalidFormatDefinition, IOException {
        formatMap.put(name, buildRecordFormatFromFile(formatFile));
        return this;
    }

    public IFormat build(){
        if("NACHA".equals(type)){
            return new NACHAFormat();
        }else{
            return new FlatFileFormat(formatMap);
        }
    }

    private  RecordFormat  buildRecordFormat(String jsonFields )
            throws InvalidFormatDefinition {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            FormatSpec spec = mapper.readValue(jsonFields, FormatSpec.class);
            return new RecordFormat(spec);
        } catch (IOException e) {
            String message = String.format("invaild format. error: %s", e.getMessage());
            throw new InvalidFormatDefinition(message);
        }
    }

    private  RecordFormat  buildRecordFormatFromFile(String formatFile )
            throws InvalidFormatDefinition,IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            FormatSpec spec = mapper.readValue(new File(formatFile), FormatSpec.class);
            return new RecordFormat(spec);
        } catch (IOException e) {
            String message = String.format("invaild format file %s. error %s ", formatFile, e.getMessage());
            throw new InvalidFormatDefinition(message);
        }
    }

}
