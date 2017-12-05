package com.filegenerator.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SectionSpec {

    @JsonProperty("header")
    FormatSpec header;
    @JsonProperty("detail")
    FormatSpec detail;
    @JsonProperty("tailer")
    FormatSpec tailer;
}
