package com.filegenerator;

import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public class FileGenerator implements IFileGenerator {
    @Override
    public void csvFileToFormatedFile(String inFile, String outFile, String formatFile) throws IOException, InvalidFormatDefinition {

    }

    @Override
    public void cvsToWriter(Writer writer, CSVReader reader, RecordFormat format) throws IOException, InvalidFormatDefinition {

    }

    @Override
    public Object readFile(InputStream in) {
        return null;
    }

    @Override
    public Object readFile(String inFile) {
        return null;
    }
}
