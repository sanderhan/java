package com.filegenerator;

import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.common.NACHAFile;
import com.filegenerator.format.FlatFileFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class FlatFileGeneratorTest {

    @org.junit.Test
    public void parseCsvFile() {
        String testCsvFile ="C:\\Workspaces\\test\\test.csv";

        FlatFileGenerator generator = (FlatFileGenerator) FileGeneratorFactory
                .getFileGenerator("STANDARD_CSV");

        Object output;
        try {
            output = generator.readFile(new FileInputStream(testCsvFile));
            fail("Need to be asserted.");
        } catch (InvalidFormatDefinition invalidFormatDefinition) {
            invalidFormatDefinition.printStackTrace();
            fail("Invaild file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Cannot find file");
        }
    }

}