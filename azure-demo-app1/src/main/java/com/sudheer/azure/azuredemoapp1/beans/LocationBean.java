package com.sudheer.azure.azuredemoapp1.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationBean {
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lan")
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
