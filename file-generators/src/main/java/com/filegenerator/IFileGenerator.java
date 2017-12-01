package com.filegenerator;

import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IFileGenerator {
    public void resultSetToStringBuilder(StringBuilder sb, ResultSet rs, RecordFormat format)
            throws SQLException;

    public void dataSetToStringBuilder(StringBuilder sb, List<List<Object>> dataset, RecordFormat format);

    public void dataSetToWriter(Writer writer, List<List<Object>> dataset, RecordFormat format)
            throws IOException;

    public void resultSetToWriter(Writer writer, ResultSet rs, RecordFormat format)
            throws SQLException, IOException;

    public void cvsToWriter(Writer writer, CSVReader reader, RecordFormat format)
            throws IOException;

    public void cvsToFile(String inCsvFile, String outFile, String formatFile)
            throws IOException, InvalidFormatDefinition;
}
