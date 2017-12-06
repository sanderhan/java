package com.filegenerator;

import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlatFileGenerator{



    public void resultSetToStringBuilder(StringBuilder sb, ResultSet rs, RecordFormat format) throws SQLException {
        format.format(sb,rs);
    }

    public void dataSetToStringBuilder(StringBuilder sb, List<List<Object>> dataset, RecordFormat format) {
        format.formatDataSet(sb,dataset);
    }

    public void dataSetToWriter(Writer writer, List<List<Object>> dataset, RecordFormat format) throws IOException {
        for(List<Object> row : dataset){
            String line = format.format(row);
            writer.write(line);
        }
    }

    public void resultSetToWriter(Writer writer, ResultSet rs, RecordFormat format) throws SQLException, IOException {
        int numOfCol = rs.getMetaData().getColumnCount();

        while(rs.next()){
            List<Object> row = new ArrayList<Object>();
            for(int i=0; i<numOfCol; i++){
                row.add(rs.getObject(i));
            }
            String line = format.format(row);
            writer.write(line);
        }

    }

    public void cvsToWriter(Writer writer, CSVReader reader,  RecordFormat format)
            throws IOException, InvalidFormatDefinition {
        String[] nextLine;
        int lineNumber =1;
        while ((nextLine = reader.readNext()) != null) {
            try {
                Object[] values = format.parseValues(nextLine);
                String line = format.format(values);
                writer.write(line);
                lineNumber++;
            } catch (ParseException e) {
                e.printStackTrace();
                String message = String.format("Parsing line %d failed.", lineNumber);
                throw new InvalidFormatDefinition(message);
            }
        }
    }

    public void cvsToFile(String inCsvFile, String outFile, String formatFile)
            throws IOException, InvalidFormatDefinition {

        RecordFormat ft = FormatBuilder.buildRecordFormatFromFile(formatFile);

        CSVReader reader = new CSVReader(new FileReader(inCsvFile));
        FileWriter writer = new FileWriter(outFile);
        try {
            cvsToWriter(writer, reader, ft);
            writer.flush();
        }finally {
            if(reader != null)reader.close();
            if(writer != null)writer.close();
        }
    }

    public List<Object[]> readToDataSet(InputStream in, RecordFormat format) throws InvalidFormatDefinition {
        Scanner sc = new Scanner(in);
        sc.useDelimiter(String.valueOf(format.getRecordTerminator().toString()));
        int lineNumber = 0;
        List<Object[]> result = new ArrayList<Object[]>();
        while(sc.hasNext()){
            String line = sc.nextLine();
            lineNumber++;

            try {
                Object[] values = format.parseLine(line);
                result.add(values);
            } catch (ParseException e) {
                throw new InvalidFormatDefinition(String.format("Parsing line# %d failed, erro:%s", lineNumber, e.getMessage()));
            }
        }

        return result;
    }


    public List<Object[]> readFileToDataSet(String inFile, String formatFile)
            throws InvalidFormatDefinition, IOException {

        RecordFormat ft = FormatBuilder.buildRecordFormatFromFile(formatFile);
        InputStream in = new FileInputStream(inFile);
        return readToDataSet(in, ft);
    }


}
