package com.filegenerator;

import com.filegenerator.common.NACHAFile;
import com.filegenerator.format.NACHAFormat;

import java.io.InputStream;
import java.text.ParseException;

public class NACHAGenerator  {


    public NACHAFile readNACHAFile(InputStream in) throws InvalidFormatDefinition, ParseException {

        NACHAFormat ft = new NACHAFormat();

        NACHAFile file = ft.parseNACHAFile(in);

        return file;

    }

}
