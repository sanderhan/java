package com.filegenerator;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FileGeneratorTest {
    @Test
    public void readFileToDataSet() throws Exception {
        String testFile = "C:\\Workspaces\\test\\test_2.txt";
        String formatFile = "C:\\Workspaces\\test\\test_spec.json";
        List<Object[]> actual = FileGeneratorBuilder.ceateFileGenerator().readFileToDataSet(testFile, formatFile);
        Assert.assertEquals(21, actual.size());
    }

}