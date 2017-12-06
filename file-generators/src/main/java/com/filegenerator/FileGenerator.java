package com.filegenerator;


import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.format.IFormat;

import java.io.*;

public abstract class FileGenerator<T> implements IFileGenerator {


    public abstract IFormat<T> getFormatter();

    @Override
    public void writeFile(Writer writer, Object inObject) throws IOException {
        getFormatter().format(writer, (T) inObject);
    }

    @Override
    public void writeFile(String outFile, Object inObject) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(outFile);
            writeFile(writer, inObject);
        }finally {
            if(writer != null)writer.close();
        }
    }

    @Override
    public T readFile(InputStream in)
            throws InvalidFormatDefinition {
        return getFormatter().parse(in);
    }

    @Override
    public T readFile(String inFile)
            throws IOException, InvalidFormatDefinition {
        FileInputStream in = null;
        try {
            in = new FileInputStream(inFile);
            return readFile(in);
        }finally {
            if(in != null) {
                in.close();
            }
        }
    }
}
