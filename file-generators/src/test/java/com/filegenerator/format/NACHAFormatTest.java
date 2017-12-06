package com.filegenerator.format;

import com.filegenerator.FileGenerator;
import com.filegenerator.FileGeneratorFactory;
import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.NACHAGenerator;
import com.filegenerator.common.NACHAFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class NACHAFormatTest {


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
        assertEquals(file.getBatches().size(),1);
    }
}