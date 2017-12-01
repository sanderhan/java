package com.filegenerator.json;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldSpec {
    @JsonProperty("index")
    int index = -1;

    @JsonProperty("name")
    String name;

    @JsonProperty("pattern")
    String pattern;

    @JsonProperty("width")
    int width = -1;

    @JsonProperty("padchar")
    CharSequence padChar = " ";

    @JsonProperty("align")
    String align = "LEFT";

    @JsonProperty("type")
    String type = "STRING";

}
