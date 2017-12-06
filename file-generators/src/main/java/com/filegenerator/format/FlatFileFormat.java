package com.filegenerator.format;

import com.filegenerator.common.*;
import com.sun.prism.impl.Disposer;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.ParseException;
import java.util.*;

public class FlatFileFormat implements IFormat<Map<String,List<Object[]>>>{

    public static FlatFileFormat STANDARD_CSV_FORMAT = new FlatFileFormat(new HashMap<String, RecordFormat>(){{
          put("DETAIL", RecordFormat.CSV);
    }});

    Map<String,RecordFormat> formatterMap = new HashMap<>();


    public FlatFileFormat(Map<String,RecordFormat>  namedFormats){
        this.formatterMap = namedFormats;
    }

    @Override
    public void format(StringBuilder sb, Map<String, List<Object[]>> dataSets) {
        for (Map.Entry<String, List<Object[]>> entry : dataSets.entrySet())
        {
            String dataSetName = entry.getKey();
            List dataSet = entry.getValue();
            RecordFormat rf = formatterMap.get(dataSetName);
            rf.formatDataSet(sb, dataSet);
        }
    }

    @Override
    public void format(Writer writer, Map<String, List<Object[]>> dataSets)
            throws IOException {
            StringBuilder sb = new StringBuilder();
            format(sb, dataSets);
            writer.append(sb.toString());
    }

    public List<Object[]>  parseNamedDataSet(String name, InputStream in) throws InvalidFormatDefinition {


        RecordFormat format = this.formatterMap.get(name);
        if(format == null)
            format = RecordFormat.CSV;

        Scanner sc = new Scanner(in);

        sc.useDelimiter(format.getRecordTerminator().toString());

        List<Object[]> result = new ArrayList<>();

        while(sc.hasNext()){
            String recordLine = sc.nextLine();
            try {
                Object[] lineValues = format.parseLine(recordLine);
                result.add(lineValues);
            } catch (ParseException e) {
                throw new InvalidFormatDefinition(e.getMessage());
            }
        }

        return result;
    }

    @Override
    public Map<String,List<Object[]>>  parse(InputStream in) throws InvalidFormatDefinition {

        List<Object[]> details  = parseNamedDataSet("DETAIL", in);
        Map<String,List<Object[]>> result = new HashMap<>();
        result.put("DETAIL",details);

        return result;
    }

}
