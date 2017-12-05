package com.filegenerator.format;

import com.filegenerator.FileGeneratorFactory;
import com.filegenerator.InvalidFormatDefinition;
import com.filegenerator.NACHAGenerator;
import com.filegenerator.common.NACHAFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class NACHAFormatTest {

    @org.junit.Test
    public void formatAddenda() {
        fail("Not implement yet,");
    }

    @org.junit.Test
    public void format() {
        fail("Not implement yet,");
    }

    @org.junit.Test
    public void parseNACHAFile() {
        String testACHFile ="C:\\Workspaces\\test\\1521422559orig_TGE1215A.ach";

        NACHAGenerator generator = FileGeneratorFactory.ceateNACHAGenerator()
        try {
            NACHAFile file = generator.readNACHAFile(new FileInputStream(testACHFile));
        } catch (InvalidFormatDefinition invalidFormatDefinition) {
            invalidFormatDefinition.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fail("Not implement yet,");
    }
}