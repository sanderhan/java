package com.filegenerator;

import java.util.List;

public interface IFileGenerator {
    public String generateToString(List<List<Object>> dataset);
    public void generateToStringBuilder(StringBuilder sb, List<List<Object>> dataset);
}
