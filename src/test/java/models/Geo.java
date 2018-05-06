package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo {
    @JsonProperty("lat") public String lat;
    @JsonProperty("lng") public String lng;

    public String compareGeo(Geo thatGeo) {
        String msg = "";
        if (!this.lat.equals(thatGeo.lat))
            msg += "Latitude is not equal. " + this.lat + " vs " + thatGeo.lat + "\r\n";
        if (!this.lng.equals(thatGeo.lng))
            msg += "Longitude is not equal. " + this.lng + " vs " + thatGeo.lng + "\r\n";

        return msg;
    }
}
