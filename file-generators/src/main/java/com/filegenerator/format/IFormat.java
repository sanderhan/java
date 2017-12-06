package com.filegenerator.format;

import com.filegenerator.common.InvalidFormatDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

public interface IFormat <T> {

    public void format(StringBuilder sb, T inObject);
    public void format(Writer writer, T inObject) throws IOException;
    public T parse(InputStream in) throws InvalidFormatDefinition;
}
