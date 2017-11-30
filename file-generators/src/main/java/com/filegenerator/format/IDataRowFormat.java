package com.filegenerator.format;

public interface IDataRowFormat {
    public String format(Object ... values);
    public void format(StringBuilder sb, Object ... values);

}
