package com.filegenerator;

import com.filegenerator.format.RecordFormat;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FormatBuilderTest {
    @Test
    public void buildRecordFormat() throws Exception {
        String csvSpec = "{\n" +
                "  \"type\":\"csv\", \"delimiter\":\",\", \"terminator\":\"\\n\", \"quoted\":false,\n" +
                "  \"fields\":[\n" +
                "    {\"index\":1,\"pattern\":\"%3d\",\"width\":-1, \"padchar\":\"\", \"align\":\"left\", \"type\":\"numeric\"},\n" +
                "    {\"index\":2,\"pattern\":\"\",\"width\":10, \"padchar\":\"\", \"align\":\"left\", \"type\":\"string\"},\n" +
                "    {\"index\":3,\"pattern\":\"\", \"type\":\"numeric\"},\n" +
                "    {\"index\":4,\"pattern\":\"\",\"pattern\":\"yyyy-MM-dd\", \"align\":\"left\", \"type\":\"date\"}\n" +
                "  ]\n" +
                "}";

        RecordFormat ft = FormatBuilder.buildRecordFormat(csvSpec);

        Assert.fail("Not implement yet.");
    }

}