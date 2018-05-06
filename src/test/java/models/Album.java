package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Album {
    @JsonProperty("userId") public int userId;
    @JsonProperty("id") public int id;
    @JsonProperty("title") public String title;

    public static class AlbumParser {
        public static List<Album> parseMultipleUsersFromJson(String strAlbums) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            List<Album> albums = mapper.readValue(strAlbums, new TypeReference<List<User>>(){});
            return albums;
        }
    }
}
