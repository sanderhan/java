package com.filegenerator.format;

import com.filegenerator.FileGeneratorBuilder;
import com.filegenerator.InvalidFormatDefinition;
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

        NACHAGenerator generator = FileGeneratorBuilder.ceateNACHAGenerator();
        NACHAFile file = null;
        try {
            file = generator.readNACHAFile(new FileInputStream(testACHFile));
        } catch (InvalidFormatDefinition invalidFormatDefinition) {
            invalidFormatDefinition.printStackTrace();
            fail("InvalidaFormat");
        } catch (ParseException e) {
            e.printStackTrace();
            fail("InvalidaFormat: parsing ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("cannot find file.");
        }
        assertEquals(file.getBatches().size(),1);
    }
}