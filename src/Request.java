public class Request {
    private String path;
    private String protocol;
    private String method;





    public Request(String path, String protocol, String method) {
        this.path = path;
        this.protocol = protocol;
        this.method = method;


    }

    public Request() {

    }

    public String getProtocol() {
        return protocol;

    }
    public String setProtocol(String protocol) {
        return protocol;
    }

    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String s) {
        this.method = method;
    }
}
