package com.filegenerator;

import com.filegenerator.common.NACHAFile;
import org.apache.commons.cli.*;


public class FileGeneratorConsoleApplication {

    public static void main(String[] args)
            throws Exception {

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

        CommandLine commandLine = parser.parse(options, args);
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
        IFileGenerator generator = FileGeneratorFactory.getFileGenerator("NACHA");
        NACHAFile f = (NACHAFile) generator.readFile(infile);

        System.out.print(f.getImmediateOriginName());

    }


}
