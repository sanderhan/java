package com.filegenerator;

import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.format.FlatFileFormat;
import com.filegenerator.format.IFormat;
import com.filegenerator.format.RecordFormat;

import java.io.IOException;

public class FileGeneratorFactory {

    public static IFileGenerator getFileGenerator(String type){
        switch(type){
            case "NACHA" :
                return new NACHAGenerator();
            case "STANDARD_CSV" :
                IFormat csvFormat = FormatBuilder.getInstance(type)
                        .addFormat("DETAIL", RecordFormat.CSV)
                        .build();
                return new FlatFileGenerator((FlatFileFormat) csvFormat);
            case "CSV_WITH_HEADER" :
                IFormat csvWithHeaderFormat = FormatBuilder.getInstance(type)
                        .addFormat("HEADER", RecordFormat.CSV)
                        .addFormat("DETAIL", RecordFormat.CSV)
                        .build();
                return new FlatFileGenerator((FlatFileFormat) csvWithHeaderFormat);
        }

        return null;
    }

    public static IFileGenerator getFileGenerator(String type, String formatFile)
            throws IOException, InvalidFormatDefinition {
            IFormat format = FormatBuilder.getInstance(type)
                    .importJsonFormatFile("DETAIL",formatFile)
                    .build();
            return new FlatFileGenerator((FlatFileFormat) format);
    }

}
