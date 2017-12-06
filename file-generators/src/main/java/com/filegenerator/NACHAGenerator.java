package com.filegenerator;

import com.filegenerator.common.NACHAFile;
import com.filegenerator.format.NACHAFormat;
import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.ParseException;

public class NACHAGenerator implements IFileGenerator {


    @Override
    public void csvFileToFormatedFile(String inFile, String outFile, String formatFile) throws IOException, InvalidFormatDefinition {

    }

    @Override
    public void cvsToWriter(Writer writer, CSVReader reader, RecordFormat format) throws IOException, InvalidFormatDefinition {

    }

    @Override
    public NACHAFile readFile(InputStream in) throws InvalidFormatDefinition {

        NACHAFormat ft = new NACHAFormat();

        NACHAFile file;
        try {
            file = ft.parseNACHAFile(in);
        } catch (ParseException e) {
            throw new InvalidFormatDefinition(String.format("Parsing error:%s" ,e.getMessage()));
        }
        return file;

    }

    @Override
    public Object readFile(String inFile) {
        return null;
    }

}
