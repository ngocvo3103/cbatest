package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
    @JsonProperty("name") public String name;
    @JsonProperty("catchPhrase") public String catchPhrase;
    @JsonProperty("bs") public String bs;

    public String compareCompany(Company thatCompany) {
        String msg = "";
        if (!this.name.equals(thatCompany.name))
            msg += "Company name is not equal. " + this.name + " vs " + thatCompany.name + "\r\n";
        if (!this.catchPhrase.equals(thatCompany.catchPhrase))
            msg += "Catch phrase is not equal. " + this.catchPhrase + " vs " + thatCompany.catchPhrase + "\r\n";
        if (!this.bs.equals(thatCompany.bs))
            msg += "BS is not equal. " + this.bs + " vs " + thatCompany.bs + "\r\n";

        return msg;
    }
}