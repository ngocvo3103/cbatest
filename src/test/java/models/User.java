package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class User {
    @JsonProperty("id") public int id;
    @JsonProperty("name") public String name;
    @JsonProperty("username") public String username;
    @JsonProperty("email") public String email;
    @JsonProperty("address") public Address address;
    @JsonProperty("phone") public String phone;
    @JsonProperty("website") public String website;
    @JsonProperty("company") public Company company;

    public String compareUserInfo(User thatUser) {
        String msg = "";
        if (this.id != thatUser.id)
            msg += "User Id is not equal. " + this.id + " vs " + thatUser.id + "\r\n";
        if (!this.name.equals(thatUser.name))
            msg += "Name is not equal. " + this.name + " vs " + thatUser.name + "\r\n";
        if (!this.username.equals(thatUser.username))
            msg += "User name is not equal. " + this.username + " vs " + thatUser.username + "\r\n";
        if (!this.email.equals(thatUser.email))
            msg += "Email is not equal. " + this.email + " vs " + thatUser.email + "\r\n";
        if (!this.phone.equals(thatUser.phone))
            msg += "Phone is not equal. " + this.phone + " vs " + thatUser.phone + "\r\n";
        if (!this.website.equals(thatUser.website))
            msg += "Website is not equal. " + this.website + " vs " + thatUser.website + "\r\n";

        msg += this.address.compareAddress(thatUser.address);
        msg += this.company.compareCompany(thatUser.company);

        return msg;
    }

    public static class UserParser {
        public static User parseUserFromJson(String strPost) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(strPost, User.class);
            return user;
        }

        public static List<User> parseMultipleUsersFromJson(String strPost) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            List<User> users = mapper.readValue(strPost, new TypeReference<List<User>>(){});
            return users;
        }

        public static String parseUserToJson(User user) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            String strJson = mapper.writeValueAsString(user);
            return strJson;
        }
    }
}


