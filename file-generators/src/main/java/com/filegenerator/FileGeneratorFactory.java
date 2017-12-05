package com.filegenerator;

public class FileGeneratorFactory {
    public static IFileGenerator ceateFileGenerator(){
        return new FileGenerator();
    }
    public static NACHAGenerator ceateNACHAGenerator(){
        return new NACHAGenerator();
    }

}
