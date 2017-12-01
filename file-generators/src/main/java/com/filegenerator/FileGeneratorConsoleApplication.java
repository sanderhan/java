package com.filegenerator;

import com.filegenerator.format.RecordFormat;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FileGeneratorConsoleApplication implements CommandLineRunner {

    @Autowired
    IFileGenerator generator;

    @Autowired
    ApplicationContext ctx;

    public static void main(String[] args) throws Exception {

        SpringApplication.run(FileGeneratorConsoleApplication.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("File Generator");


        Option optionInput = Option.builder("i").argName("in").hasArg().desc("input file").build();
        Option optionOutput = Option.builder("o").argName("out").hasArg().desc("output file").build();
        Option optionFormat = Option.builder("format").argName("format").hasArg().desc("format").build();
        Options options = new Options();

        options.addOption(optionInput);
        options.addOption(optionOutput);
        options.addOption(optionFormat);

        String infile = "";
        String outfile = "";
        String format = "";

        CommandLineParser parser = new DefaultParser();

        CommandLine commandLine = parser.parse(options, strings);
        if(commandLine.hasOption("i") ){
            infile = commandLine.getOptionValue("i");
        }
        if(commandLine.hasOption("o") ){
            outfile = commandLine.getOptionValue("o");
        }
        if(commandLine.hasOption("format") ){
            format = commandLine.getOptionValue("format");
        }

        System.out.println(String.format("Input %s, out to %s, format:%s", infile, outfile, format));
        generator.cvsToFile(infile, outfile, format);
    }
}
