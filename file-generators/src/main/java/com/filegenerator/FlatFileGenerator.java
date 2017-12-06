package com.filegenerator;

import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.format.FlatFileFormat;
import com.filegenerator.format.IFormat;
import com.filegenerator.format.RecordFormat;
import com.opencsv.CSVReader;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlatFileGenerator extends FileGenerator<Map<String, List<Object[]>>> {


    IFormat<Map<String, List<Object[]>>> flatFormatter;

    public FlatFileGenerator(FlatFileFormat format) {
        this.flatFormatter = format;
    }

    @Override
    public IFormat<Map<String, List<Object[]>>> getFormatter() {
        return flatFormatter;
    }
}

