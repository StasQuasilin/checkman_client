package checkman.constants;

public interface Links {
    String API = "/api/v1";
    String EXTERNAL = "91.222.16.30:3322/checkman";
    String INTERNAL = "10.10.10.201:3322/checkman";
    String HTTP_PROTOCOL = "http://";
    String SOCKET_PROTOCOL = "ws://";
    String SUBSCRIBE = "/api/subscriber";

    String EXTERNAL_API = HTTP_PROTOCOL + EXTERNAL;
    String INTERNAL_API = HTTP_PROTOCOL + INTERNAL;
    String EXTERNAL_SUBSCRIBE = SOCKET_PROTOCOL + EXTERNAL + SUBSCRIBE;
    String INTERNAL_SUBSCRIBE = SOCKET_PROTOCOL + INTERNAL + SUBSCRIBE;;

    String HOME = "/checkman";
    String LOGIN = "/a/sign/in";


}
