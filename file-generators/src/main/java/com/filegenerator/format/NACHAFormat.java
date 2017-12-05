package com.filegenerator.format;

import com.filegenerator.common.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class NACHAFormat {


    public static enum RecordType {

        FILE_HEADER(1),
        BATCH_HEADER(5),
        ENTRY(6),
        ADDENDA(7),
        BATCH_CONTROL(8),
        FILE_CONTROL(9);

        private int recordType;

        RecordType(int type){
            this.recordType = type;
        }

        public int getRecordType(){
            return recordType;
        }
    }


    public static  int  RECORD_SIZE = 94;
    public static  int  BLOCK_SIZE=10;
    public static  int  ADDENDA_RECORD_SIZE=80;

    public static RecordFormat NACHA_FILE_HEADER = RecordFormat.FIXED_LENGTH.withFields( new FieldFormat[] {
            new FieldFormat(1, "RecordType", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(2, "PriorityCode", "%d", 2, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(3, "ImmediateDestination", "%s", 10, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(4, "ImmediateOrigin", "%s", 10, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(5, "FileCreationDate", "yyMMdd", 6, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.DATE),
            new FieldFormat(6, "FileCreationTime", "hhmm", 4, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.TIME),
            new FieldFormat(7, "FileIDModifier", "%S", 1, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(8, "RecordSize", "%d", 3, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(9, "BlockingFactor", "%d", 2, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(10, "FormatCode", "%s", 1, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(11, "ImmediateDestinationName", "%s", 23, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(12, "ImmediateOriginName", "%s", 23, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(13, "ReferenceCode", "%s", 8, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING)
    });

    public static RecordFormat NACHA_FILE_CONTROL = RecordFormat.FIXED_LENGTH.withFields(new FieldFormat[] {
            new FieldFormat(1, "RecordType", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(2, "BatchCount", "%d", 6, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(3, "BlockCount", "%d", 6, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(4, "EntryCount", "%d", 6, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(5, "EntryHash", "%d", 10, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(6, "TotalDebit", "%d", 12, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(7, "TotalCredit", "%d", 12, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(8, "Reserved", "%s", 39, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING)
    });

    public static RecordFormat NACHA_BATCH_HEADER =  RecordFormat.FIXED_LENGTH.withFields(new FieldFormat[] {
            new FieldFormat(1, "RecordType", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(2, "ServiceClassCode", "%d", 3, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(3, "CompanyName", "%s", 16, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(4, "CoDiscData", "%s", 20, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(5, "CompanyID", "%s", 10, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(6, "SEC", "%s", 3, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(7, "EntityDescription", "%s", 10, '0', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(8, "CoDescriptiveDate", "%s", 6, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(9, "EffectiveDate", "yyMMdd", 6, ' ', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.DATE),
            new FieldFormat(10, "SettlementDate", "%d", 3, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(11, "OriginStatusCode", "%s", 1, ' ', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.STRING),
            new FieldFormat(12, "OriginDFI", "%S", 8, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.INT),
            new FieldFormat(13, "BatchNumber", "%d", 7, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT)
    });

    public static RecordFormat NACHA_BATCH_CONTROL =  RecordFormat.FIXED_LENGTH.withFields(new FieldFormat[] {
            new FieldFormat(1, "RecordType", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(2, "ServiceClassCode", "%d", 3, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(3, "EntryCount", "%6d", 6, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(4, "EntryHash", "%d", 10, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(5, "TotalDebit", "%d", 12, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(6, "TotalCredit", "%d", 12, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(7, "CompanyID", "%s", 10, '0', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(8, "MessageAuthenticationCode", "%s", 19, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(9, "Reserved", "%s", 6, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(10, "OriginDFI", "%s", 8, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(11, "BatchNumber", "%d", 7, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT)
    });

    public static RecordFormat NACHA_ENTRY = RecordFormat.FIXED_LENGTH.withFields(new FieldFormat[] {
            new FieldFormat(1, "RecordType", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(2, "TransactionCode", "%2d", 2, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(3, "ReceivingDFI", "%s", 8, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(4, "CheckDigit", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(5, "DFIAccount", "%s", 17, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(6, "Amount", "%d", 10, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(7, "IndividualIdentificationNumber", "%s", 15, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(8, "IndividualName", "%s", 22, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(9, "DiscretionaryData", "%s", 2, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(10, "AddendaIndicator", "%s", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(11, "OriginDFI", "%s", 8, ' ', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.STRING),
            new FieldFormat(11, "Sequence", "%s", 7, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING)
    });

    public static RecordFormat NACHA_ADDENDA = RecordFormat.FIXED_LENGTH.withFields(new FieldFormat[] {
            new FieldFormat(1, "RecordType", "%d", 1, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(2, "AddendaTypeCode", "%d", 2, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(3, "PaymentInfo", "%s", 80, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
            new FieldFormat(4, "AddendaSequence", "%d", 4, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT),
            new FieldFormat(5, "EntityDetailSequence", "%d", 7, '0', FieldFormat.Alignment.RIGHT, FieldFormat.DataType.INT)
    });


    private boolean isCredit(int transactionCode){
        switch(transactionCode){
            case 21:
            case 22:
            case 23:
            case 24:
            case 31:
            case 32:
            case 33:
            case 34:
            case 41:
            case 42:
            case 43:
            case 44:
            case 51:
            case 52:
            case 53:
            case 54:
                return true;
            default:
                return false;
        }
    }

    public static RecordFormat NACHA_FILLING =  new RecordFormat("","9","\n",false).withFields(new FieldFormat[] {
            new FieldFormat(2, "Filling", "%s", 93, ' ', FieldFormat.Alignment.LEFT, FieldFormat.DataType.STRING),
    });

    public List<NACHAAddenda> formatAddenda(int entrySequence, String addendaType, String addenda){
        List<NACHAAddenda> addendas = new ArrayList<NACHAAddenda>();
        int addendCount =addenda.length()/ADDENDA_RECORD_SIZE +1;
        int addendaSeq = 0;
        int start = 0;
        for(int i=0; i< addendCount ; i++){
            addendaSeq++;
            NACHAAddenda addendaRecord = new NACHAAddenda();
            addendaRecord.setAddendaTypeCode(addendaType);
            addendaRecord.setAddendaSequence(addendaSeq);
            addendaRecord.setEntrySequence(entrySequence);

            String paymentInfor = addenda.substring(start, start+ADDENDA_RECORD_SIZE);
            start = start + ADDENDA_RECORD_SIZE;
            addendas.add(addendaRecord);
        }

        return addendas;

    }


    public void format(StringBuilder sb , NACHAFile file, NACHACompanyBatch [] batches){
        Object[] header = file.toFileHeaderArray();
        NACHA_FILE_HEADER.format(sb, header);

        int batchCount = 0;
        int blockCount = 0;
        int entryCount = 0;

        int recordCount =0;

        BigDecimal totalDebits = BigDecimal.valueOf(0);
        BigDecimal totalCredits = BigDecimal.valueOf(0);
        BigInteger entryHashSum = BigInteger.valueOf(0);

        for(NACHACompanyBatch batch : batches){
            batchCount++;
            batch.setBatchNumber(batchCount);
            NACHA_BATCH_HEADER.format(sb, batch.toBatchHeaderArray());

            int batchEntryCount = 0;
            int batchAddendaCount = 0;
            BigDecimal batchTotalDebits = BigDecimal.valueOf(0);
            BigDecimal batchTotalCredits = BigDecimal.valueOf(0);

            for(NACHAEntry entry : batch.getEntries()) {
                batchEntryCount++;
                entry.setSequence(batchEntryCount);
                entry.setOriginDFI(batch.getOriginDFI());

                NACHA_ENTRY.format(sb,entry.toArray());

                if (isCredit(entry.getTransactionCode())) {
                    batchTotalCredits.add(entry.getAmount());
                } else {
                    batchTotalDebits.add(entry.getAmount());
                }

                if(entry.hasAddenda()){
                    List<NACHAAddenda> addendas = formatAddenda(batchEntryCount,entry.getAddendaType(),entry.getAddenda() );
                    for(NACHAAddenda addenda : addendas){
                        NACHA_ADDENDA.format(sb, addenda.toArray());
                        batchAddendaCount++;
                    }
                }

            }
            NACHABatchControl batchControl = new NACHABatchControl();
            batchControl.setBatchNumber(batchCount);
            batchControl.setEntryCount(batchAddendaCount >0? batchAddendaCount: batchEntryCount);
            batchControl.setOriginDFI(batch.getOriginDFI());
            batchControl.setServiceClassCode(batch.getServiceClassCode());
            batchControl.setTotalCredits(batchTotalCredits);
            batchControl.setTotalDebits(batchTotalDebits);

            totalCredits.add(batchTotalCredits);
            totalDebits.add(batchTotalDebits);

            NACHA_BATCH_CONTROL.format(sb, batchControl.toArray());
            recordCount  = recordCount + batchEntryCount + batchAddendaCount;

        }

        recordCount = recordCount +2;
        blockCount = recordCount/BLOCK_SIZE +1;

        NACHAFileControl fileControl = new NACHAFileControl();
        fileControl.setBatchCount(batchCount);
        fileControl.setBlockCount(blockCount);
        fileControl.setEntryCount(entryCount);
        fileControl.setTotalCredits(totalCredits);
        fileControl.setTotalDebits(totalDebits);


        fileControl.setEntryHash(entryHashSum);

        NACHA_FILE_CONTROL.format(sb,fileControl.toArray());

        int fillingCount = recordCount%BLOCK_SIZE;
        for(int i=0; i< fillingCount; i++){
            NACHA_FILLING.format(sb,"9");
        }
    }


    public NACHAFile parseNACHAFile(InputStream in) throws ParseException {

        Scanner sc = new Scanner(in);
        sc.useDelimiter("\n");
        NACHAFile file = new NACHAFile();
        while(sc.hasNext()){
            String recordLine = sc.nextLine();
            int recordType = Integer.valueOf((recordLine.substring(0,1)));
            switch(recordType){
                case 1:
                    Object[] header = NACHA_FILE_HEADER.parseLine(recordLine);
                    break;
                case 5:
                    Object[] batchHeader = NACHA_BATCH_HEADER.parseLine(recordLine);
                    break;
                case 6:
                    Object[] entries = NACHA_ENTRY.parseLine(recordLine);
                    break;
                case 7:
                    Object[] addenda = NACHA_ADDENDA.parseLine(recordLine);
                    break;
                case 8:
                    Object[] batchControl = NACHA_BATCH_CONTROL.parseLine(recordLine);
                    break;
                case 9:
                    Object[] fileControl = NACHA_FILE_CONTROL.parseLine(recordLine);
                    break;
            }
        }


        return file;
    }

}
