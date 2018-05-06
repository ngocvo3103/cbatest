package api;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

abstract class APIBase {
    public HttpResponse get(String endpoint, int okCode) {
        HttpResponse response = null;
        try {
            HttpURLConnection request = openConnection(endpoint);
            request.setRequestMethod("GET");
            response = getResponse(request, okCode);
        } catch (Exception ex) {
            //todo: Log exception
            System.out.println(ex.getCause());
            System.out.println(ex.getStackTrace());
        }
        return response;
    }

    public HttpResponse post(String endpoint, String body, Map<String, String> header, int okCode) {
        HttpResponse response = null;
        try {
            HttpURLConnection request = openConnection(endpoint);
            request.setDoOutput(true);
            request.setRequestMethod("POST");
            HttpURLConnection fullRequest = addHeader(header, request);
            OutputStream outputStream = fullRequest.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream), true);
            writer.append(body).flush();
            response = getResponse(fullRequest, okCode);
        } catch (Exception ex) {
            //todo: Log exception
            System.out.println(ex.getCause());
            System.out.println(ex.getStackTrace());
        }
        return response;
    }

    public HttpResponse put(String endpoint, String body, Map<String, String> header, int okCode) {
        HttpResponse response = null;
        try {
            HttpURLConnection request = openConnection(endpoint);
            request.setDoOutput(true);
            request.setRequestMethod("PUT");
            HttpURLConnection fullRequest = addHeader(header, request);
            OutputStream outputStream = fullRequest.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream), true);
            writer.append(body).flush();
            response = getResponse(fullRequest, okCode);
        } catch (Exception ex) {
            //todo: Log exception
            System.out.println(ex.getCause());
            System.out.println(ex.getStackTrace());
        }
        return response;
    }

    public HttpResponse delete(String endpoint, int okCode) {
        HttpResponse response = null;
        try {
            HttpURLConnection request = openConnection(endpoint);
            request.setRequestMethod("delete");
            response = getResponse(request, okCode);
        } catch (Exception ex) {
            //todo: Log exception
            System.out.println(ex.getCause());
        }
        return response;
    }

    private HttpURLConnection openConnection(String endpoint) throws Exception {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(endpoint);
            connection = (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException mex){
            //todo: Log exception
            System.out.println(mex.getCause());
            throw mex;
        } catch (IOException iex) {
            //todo: Log exception
            System.out.println(iex.getCause());
            throw iex;
        }
        return connection;
    }

    private HttpURLConnection addHeader(Map<String, String> header, HttpURLConnection request) {
        for (Map.Entry<String, String> entry : header.entrySet()) {
            request.setRequestProperty(entry.getKey(), entry.getValue());
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        return request;
    }

    private HttpResponse getResponse(HttpURLConnection request, int okCode) throws Exception {
        int httpCode = request.getResponseCode();
        if (httpCode == okCode) {
            return new HttpResponse(httpCode, readHttpContent(request.getInputStream()));
        } else if (request.getErrorStream() != null) {
            return new HttpResponse(httpCode, readHttpContent(request.getErrorStream()));
        } else {
            return new HttpResponse(httpCode, "");
        }
    }

    private String readHttpContent(InputStream stream) throws Exception {
        String content = "";
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(stream, writer);
            content = writer.toString();
        } catch (IOException ex) {
            //todo: Log exception
            System.out.println(ex.getMessage());
            throw ex;
        }
        return content;
    }
}
