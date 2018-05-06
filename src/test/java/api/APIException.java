package api;

public class APIException extends Exception {
    private HttpResponse _response;
    public APIException() {}
    public APIException(HttpResponse response, String message) {
        super(message);
        _response = response;
    }

    public HttpResponse getHttpResponse() {
        return _response;
    }
}
