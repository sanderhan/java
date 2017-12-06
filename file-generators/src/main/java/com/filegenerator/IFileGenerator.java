package com.filegenerator;

import com.filegenerator.common.InvalidFormatDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public interface IFileGenerator<T> {


    public void writeFile(Writer writer,T inObject) throws IOException;
    public void writeFile(String outFile,T inObject) throws IOException;

    public T readFile(InputStream in) throws InvalidFormatDefinition;
    public T readFile(String inFile) throws IOException, InvalidFormatDefinition;

}
