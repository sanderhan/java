package com.filegenerator;

import com.filegenerator.common.InvalidFormatDefinition;
import com.filegenerator.common.NACHAFile;
import com.filegenerator.format.IFormat;

import java.io.InputStream;
import java.text.ParseException;

public class NACHAGenerator extends FileGenerator<NACHAFile> {

    IFormat<NACHAFile> nachaFormatter = null;
    public NACHAGenerator(){
        this.nachaFormatter = FormatBuilder.getInstance("NACHA").build();
    }


    @Override
    public IFormat<NACHAFile> getFormatter() {
        return nachaFormatter;
    }
}
