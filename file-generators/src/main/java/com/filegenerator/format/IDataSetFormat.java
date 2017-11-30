package com.filegenerator.format;

import java.util.List;

public interface IDataSetFormat {

    public String format(List<List<Object>> dataset);
    public String format(List<List<Object>> dataset, List<String> header);

    public void format(StringBuilder sb, List<List<Object>> dataset);
    public void format(StringBuilder sb, List<List<Object>> dataset, List<String>header);
}
