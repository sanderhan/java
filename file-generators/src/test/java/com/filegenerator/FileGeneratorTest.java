package com.filegenerator;

import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.common.NACHAFile;
import com.filegenerator.format.FlatFileFormat;
import com.filegenerator.format.IFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FileGeneratorTest {

    @org.junit.Test
    public void parseCsvFile() {
        String testCsvFile ="C:\\Workspaces\\test\\test.csv";

        FlatFileGenerator generator = (FlatFileGenerator) FileGeneratorFactory
                .getFileGenerator("STANDARD_CSV");

        Map<String, List<Object[]>> output;
        try {
            output = generator.readFile(new FileInputStream(testCsvFile));
            assertEquals(21, output.get("DETAIL").size());
        } catch (InvalidFormatDefinition invalidFormatDefinition) {
            invalidFormatDefinition.printStackTrace();
            fail("Invaild file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Cannot find file");
        }
    }

    @org.junit.Test
    public void parseFixedWidthFile() {
        String fixedFile ="C:\\Workspaces\\test\\test_2.txt";
        String formatFile = "C:\\Workspaces\\test\\test_spec.json";
        Map<String, List<Object[]>> output;
        try {
            FlatFileGenerator generator = (FlatFileGenerator) FileGeneratorFactory
                    .getFileGenerator("FIXED_TEST", formatFile);

            output = generator.readFile(new FileInputStream(fixedFile));
            assertEquals(21, output.get("DETAIL").size());
        } catch (InvalidFormatDefinition invalidFormatDefinition) {
            invalidFormatDefinition.printStackTrace();
            fail("Invaild file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Cannot find file");
        } catch (IOException e) {
            e.printStackTrace();
            fail("Cannot read file");
        }
    }


    @org.junit.Test
    public void parseNACHAFile() {
        String testACHFile ="C:\\Workspaces\\test\\1521422559orig_TGE1215A.ach";

        NACHAGenerator generator = (NACHAGenerator) FileGeneratorFactory.getFileGenerator("NACHA");
        NACHAFile file = null;
        try {
            file = generator.readFile(new FileInputStream(testACHFile));
            assertEquals(1, file.getBatches().size());
        } catch (InvalidFormatDefinition invalidFormatDefinition) {
            invalidFormatDefinition.printStackTrace();
            fail("Invaild NACHA file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Cannot find NACHA file");
        }
    }
}