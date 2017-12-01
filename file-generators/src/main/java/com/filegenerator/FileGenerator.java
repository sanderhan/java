package com.filegenerator;

import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileGenerator implements IFileGenerator {



    @Override
    public void resultSetToStringBuilder(StringBuilder sb, ResultSet rs, RecordFormat format) throws SQLException {
        format.format(sb,rs);
    }

    @Override
    public void dataSetToStringBuilder(StringBuilder sb, List<List<Object>> dataset, RecordFormat format) {
        format.formatDataSet(sb,dataset);
    }

    @Override
    public void dataSetToWriter(Writer writer, List<List<Object>> dataset, RecordFormat format) throws IOException {
        for(List<Object> row : dataset){
            String line = format.format(row);
            writer.write(line);
        }
    }

    @Override
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

    @Override
    public void cvsToWriter(Writer writer, CSVReader reader,  RecordFormat format) throws IOException {
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null) {
            String line = format.format(nextLine);
            writer.write(line);
        }
    }

    @Override
    public void cvsToFile(String inCsvFile, String outFile, String formatFile)
            throws IOException, InvalidFormatDefinition {
        CSVReader reader = new CSVReader(new FileReader(inCsvFile));
        FileWriter writer = new FileWriter(outFile);
        try {
            RecordFormat ft = FormatBuilder.buildRecordFormatFromFile(formatFile);
            cvsToWriter(writer, reader, ft);
        }catch(IOException e) {
            if(reader != null)reader.close();
            if(writer != null)writer.close();
            throw e;
        }
    }

}
