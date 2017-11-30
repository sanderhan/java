package com.filegenerator.format;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class DataFieldFormat {

    public static enum Alignment { LEFT,RIGHT}

    String format = null;
    char padChar = ' ';
    int offset = -1;
    int width = -1;
    Alignment alignment = Alignment.LEFT;

    public String format(Object value){
        String formatted = value.toString();
        if(format != null){
            formatted = String.format(format, value);
        }

        if(width > 0){
            if(formatted.length() > width){
                formatted = ( alignment == Alignment.LEFT ? StringUtils.left(formatted, width) : StringUtils.right(formatted, width) );
            }else{
                formatted = ( alignment == Alignment.LEFT ? StringUtils.rightPad(formatted, width, padChar) : StringUtils.leftPad(formatted, width, padChar));
            }
        }

        return formatted;
    }
}
