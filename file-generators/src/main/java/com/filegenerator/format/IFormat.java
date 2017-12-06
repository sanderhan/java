package com.filegenerator.format;

import com.filegenerator.InvalidFormatDefinition;

import java.io.InputStream;

public interface IFormat <T> {

    public void format(StringBuilder sb, T inObject);
    public T parse(InputStream in) throws InvalidFormatDefinition;
}
