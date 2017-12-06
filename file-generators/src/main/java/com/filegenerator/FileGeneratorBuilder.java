package com.filegenerator;

public class FileGeneratorBuilder {

    public static FlatFileGenerator ceateFileGenerator(){
        return new FlatFileGenerator();
    }
    public static NACHAGenerator ceateNACHAGenerator(){
        return new NACHAGenerator();
    }

}
