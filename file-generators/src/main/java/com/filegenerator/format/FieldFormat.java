package com.filegenerator.format;

import com.filegenerator.json.FieldSpec;
import com.sun.org.apache.xerces.internal.util.MessageFormatter;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.*;
import java.util.logging.Logger;

@Getter
public final class FieldFormat {

    public static Logger logger = Logger.getLogger(FieldFormat.class.getName());

    public static enum Alignment { LEFT, RIGHT}
    public static enum DataType { STRING, INT, DECIMAL, MONEY, PERCENT, DATE,TIME}


    private Format formatter = null;

    int index = -1;
    String name  =null;

    String pattern = null;
    int width = -1;
    char padChar = ' ';
    Alignment align = Alignment.LEFT;

    DataType type = DataType.STRING;

    public FieldFormat(){
    }

    public FieldFormat(FieldSpec spec){
        this.pattern = spec.getPattern();
        this.width = spec.getWidth();
        this.padChar = spec.getPadChar() !=null ? spec.getPadChar().charAt(0): ' ';
        this.align = Alignment.valueOf(spec.getAlign().toUpperCase());
        this.index = spec.getIndex();
        this.name = spec.getName();
        this.type = DataType.valueOf(spec.getType().toUpperCase());

        switch(type){

            case INT:
                formatter = NumberFormat.getInstance();
                break;
            case DECIMAL:
                formatter = DecimalFormat.getInstance();
                break;
            case MONEY:
                formatter = DecimalFormat.getCurrencyInstance();
                break;
            case PERCENT:
                formatter = DecimalFormat.getPercentInstance();
                break;
            case DATE:
            case TIME:
                if(pattern != null) {
                    formatter = new SimpleDateFormat(pattern);
                }
                else {
                    formatter = new SimpleDateFormat();
                }
                break;
            default:
                if(pattern !=null )
                    formatter = new MessageFormat(pattern);
                else
                    formatter = null;
        }

    }

    public FieldFormat(int index, String name, String pattern, int width, char padChar, Alignment align, DataType type){
        this.pattern = pattern;
        this.width = width;
        this.padChar = padChar;
        this.align = align;
        this.index = index;
        this.name = name;
        this.type = type;

         switch(type){

             case INT:
                formatter = NumberFormat.getInstance();
                 break;
             case DECIMAL:
                 formatter = new DecimalFormat();
                 break;
             case MONEY:
                 formatter = DecimalFormat.getCurrencyInstance();
                 break;
             case PERCENT:
                 formatter = DecimalFormat.getPercentInstance();
                 break;
             case DATE:
             case TIME:
                if(pattern != null) {
                    formatter = new SimpleDateFormat(pattern);
                }
                else {
                    formatter = new SimpleDateFormat();
                }
                break;
            default:
                if(pattern !=null )
                    formatter = new MessageFormat(pattern);
                else
                    formatter = null;
         }
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

    public Object parse(String value) throws ParseException {
        try {
            String paddignRegex ;
            if(align == Alignment.LEFT){
                paddignRegex = String.format("%s.+$", String.valueOf(padChar));
            }else{
                paddignRegex = String.format("^%s.+", String.valueOf(padChar));
            }
            value = value.replaceFirst(paddignRegex, "");

            if (formatter != null) {
                return formatter.parseObject(value);
            } else {
                return value;
            }
        }catch(ParseException e){
            logger.warning(String.format("cannot parse %s with pattern %s.", value, pattern));
            throw e;
        }
    }

    public String format(Object value){
        
        String formatted = value.toString();
        if(formatter != null){
            formatted = formatter.format(value);
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
