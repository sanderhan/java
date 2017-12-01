package com.filegenerator.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormatSpec {

    @JsonProperty("delimiter")
    CharSequence delimiter = "";

    @JsonProperty("recordheader")
    CharSequence recordHeader = "";

    @JsonProperty("terminator")
    CharSequence recordTerminator = "\n";

    //CSV only
    @JsonProperty("quoted")
    boolean quoted = false;

    @JsonProperty("headers")
    boolean withHeader = false;

    @JsonProperty("fields")
    List<FieldSpec> fields;

}
