package com.filegenerator;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filegenerator.format.RecordFormat;
import com.filegenerator.json.FormatSpec;

import java.io.File;
import java.io.IOException;

public class FormatBuilder {

    public static RecordFormat  buildRecordFormat(String jsonFields )
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

    public static RecordFormat  buildRecordFormatFromFile(String formatFile )
            throws InvalidFormatDefinition,IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            FormatSpec spec = mapper.readValue(new File(formatFile), FormatSpec.class);
            return new RecordFormat(spec);
        } catch (IOException e) {
            String message = String.format("invaild format file %s. ", formatFile);
            throw new InvalidFormatDefinition(message);
        }
    }
}
