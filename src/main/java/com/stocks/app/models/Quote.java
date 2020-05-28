package com.stocks.app.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quote {

    @JsonProperty("open")
    @JsonAlias({"o"})
    public float open;

    @JsonProperty("high")
    @JsonAlias({"h"})
    public float high;

    @JsonProperty("low")
    @JsonAlias({"l"})
    public float low;

    @JsonProperty("current")
    @JsonAlias({"c"})
    public float c;

    @JsonProperty("previous")
    @JsonAlias({"pc"})
    public float previous;
}