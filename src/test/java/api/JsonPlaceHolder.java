package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import models.Album;
import models.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonPlaceHolder extends APIBase {
    private String userEndpoint = "https://jsonplaceholder.typicode.com/users/";

    public User getSingleUser(int userId) throws APIException {
        User user = null;
        try {
            HttpResponse response = get(userEndpoint + userId, HttpURLConnection.HTTP_OK);
            if(response.getHttpCode() == HttpURLConnection.HTTP_OK) {
                user = User.UserParser.parseUserFromJson(response.getHttpResponse());
            } else {
                throw new APIException(response, "Error getting user.");
            }
        } catch (IOException iex) {
            //todo: handle
        } catch (APIException ex) {
            throw ex;
        }
        return user;
    }

    public List<User> getUserList() throws APIException {
        List<User> users = null;
        try {
            HttpResponse response = get(userEndpoint, HttpURLConnection.HTTP_OK);
            if(response.getHttpCode() == HttpURLConnection.HTTP_OK) {
                users = User.UserParser.parseMultipleUsersFromJson(response.getHttpResponse());
            } else {
                throw new APIException(response, "Error getting user list.");
            }
        } catch (IOException iex) {
            //todo: handle
        } catch (APIException ex) {
            throw ex;
        }
        return users;
    }

    public User createUser(User user) throws APIException {
        List<User> users = null;
        try {
            String body = User.UserParser.parseUserToJson(user);
            HttpResponse response = post(userEndpoint, body, getHeader(), HttpURLConnection.HTTP_CREATED);
            if(response.getHttpCode() == HttpURLConnection.HTTP_CREATED) {
                user = User.UserParser.parseUserFromJson(response.getHttpResponse());
            } else {
                throw new APIException(response, "Error getting user.");
            }
        } catch (IOException iex) {
            //todo: handle
        } catch (APIException ex) {
            throw ex;
        }
        return user;
    }

    public User updateUser(User user) throws APIException {
        List<User> users = null;
        try {
            String body = User.UserParser.parseUserToJson(user);
            HttpResponse response = post(userEndpoint, body, getHeader(), HttpURLConnection.HTTP_ACCEPTED);
            if(response.getHttpCode() == HttpURLConnection.HTTP_ACCEPTED) {
                user = User.UserParser.parseUserFromJson(response.getHttpResponse());
            } else {
                throw new APIException(response, "Error getting user.");
            }
        } catch (IOException iex) {
            //todo: handle
        } catch (APIException ex) {
            throw ex;
        }
        return user;
    }

    public List<Album> getUserAlbums(int userId) throws APIException {
        List<Album> albums = null;
        try {
            HttpResponse response = get(userEndpoint, HttpURLConnection.HTTP_OK);
            if(response.getHttpCode() == HttpURLConnection.HTTP_OK) {
                albums = Album.AlbumParser.parseMultipleUsersFromJson(response.getHttpResponse());
            } else {
                throw new APIException(response, "Error getting user albums.");
            }
        } catch (IOException iex) {
            //todo: handle
        } catch (APIException ex) {
            throw ex;
        }
        return albums;
    }

    public HttpResponse deleteUser(int userId) {
        HttpResponse response = delete(userEndpoint + userId, HttpURLConnection.HTTP_ACCEPTED);
        return response;
    }

    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-type", "application/json; charset=UTF-8");
        return header;
    }
}
