package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    @JsonProperty("street") public String street;
    @JsonProperty("suite") public String suite;
    @JsonProperty("city") public String city;
    @JsonProperty("zipcode") public String zipcode;
    @JsonProperty("geo") public Geo geo;

    public String compareAddress(Address thatAddress) {
        String msg = "";
        if (!this.street.equals(thatAddress.street))
            msg += "Street is not equal. " + this.street + " vs " + thatAddress.street + "\r\n";
        if (!this.suite.equals(thatAddress.suite))
            msg += "Suite is not equal. " + this.suite + " vs " + thatAddress.suite + "\r\n";
        if (!this.city.equals(thatAddress.city))
            msg += "City is not equal. " + this.city + " vs " + thatAddress.city + "\r\n";
        if (!this.zipcode.equals(thatAddress.zipcode))
            msg += "Zipcode is not equal. " + this.zipcode + " vs " + thatAddress.zipcode + "\r\n";
        msg += this.geo.compareGeo(thatAddress.geo);

        return msg;
    }
}