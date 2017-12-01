package com.filegenerator.format;

import com.filegenerator.json.FieldSpec;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public final class FieldFormat {

    public static enum Alignment { LEFT, RIGHT}
    public static enum DataType { STRING, NUMERIC, DATE}

    int index = -1;
    String name  =null;

    String pattern = null;
    int width = -1;
    char padChar = ' ';
    Alignment align = Alignment.LEFT;

    DataType type = DataType.STRING;

    public FieldFormat(){}

    public FieldFormat(FieldSpec spec){
        this.pattern = spec.getPattern();
        this.width = spec.getWidth();
        this.padChar = spec.getPadChar() !=null ? spec.getPadChar().charAt(0): ' ';
        this.align = Alignment.valueOf(spec.getAlign().toUpperCase());
        this.index = spec.getIndex();
        this.name = spec.getName();
        this.type = DataType.valueOf(spec.getType().toUpperCase());
    }

    public FieldFormat(int index, String name, String pattern, int width, char padChar, Alignment align, DataType type){
        this.pattern = pattern;
        this.width = width;
        this.padChar = padChar;
        this.align = align;
        this.index = index;
        this.name = name;
        this.type = type;
    }

    public FieldFormat withWidth(int width, char padChar, Alignment align){
        return new FieldFormat(this.index, this.name, this.pattern, width, padChar, this.align, this.type);
    }

    public FieldFormat withName(String name){
        return new FieldFormat(this.index, name, this.pattern, this.width, this.padChar, this.align, this.type);
    }
    public FieldFormat withIndex(int index){
        return new FieldFormat(index, this.name, this.pattern, this.width, this.padChar, this.align, this.type);
    }

    public FieldFormat withPattern(String pattern){
        return new FieldFormat(this.index, this.name, pattern, this.width, this.padChar, this.align, this.type);
    }

    public String format(Object value){
        
        String formatted = value.toString();
        if(pattern != null){
            formatted = String.format(pattern, value);
        }

        if(width > 0){
            if(formatted.length() > width){
                formatted = ( align == Alignment.LEFT ? StringUtils.left(formatted, width) : StringUtils.right(formatted, width) );
            }else{
                formatted = ( align == Alignment.LEFT ? StringUtils.rightPad(formatted, width, padChar) : StringUtils.leftPad(formatted, width, padChar));
            }
        }

        return formatted;
    }
}
