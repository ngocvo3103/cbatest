package api;

public class HttpResponse {
    private int _httpCode;
    private String _httpResponseContent;

    public HttpResponse(int httpCode, String httpResponseContent){
        this._httpCode = httpCode;
        this._httpResponseContent = httpResponseContent;
    }

    public int getHttpCode(){
        return this._httpCode;
    }

    public String getHttpResponse() {
        return _httpResponseContent;
    }
}
