package com.filegenerator.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileSpec {

    String fileType;

    @JsonProperty("header")
    FormatSpec fileHeader;
    @JsonProperty("sections")
    List<SectionSpec> sections;

    @JsonProperty("tailer")
    FormatSpec tailer;

}
