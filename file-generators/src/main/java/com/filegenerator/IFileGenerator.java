package com.filegenerator;

import com.filegenerator.format.IFormat;
import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public interface IFileGenerator<T> {

    IFormat formatter = null;

    public T readFromCsvFile(String inCsvFile, String formatFile)
            throws IOException, InvalidFormatDefinition;

    public T readFile(InputStream in);
    public T readFile(String inFile);

}
