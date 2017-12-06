package com.filegenerator.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileSpec {

    @JsonProperty("type")
    String fileType;

    @JsonProperty("formats")
    Map<String, FormatSpec> formats;

}
