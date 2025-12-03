package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ScenarioContext {

    private static final ThreadLocal<RequestSpecification> REQ = new ThreadLocal<>();
    private static final ThreadLocal<Response> RES = new ThreadLocal<>();

    public static void setRequest(RequestSpecification request) {
        REQ.set(request);
    }

    public static void setResponse(Response response) {
        RES.set(response);
    }

    public static RequestSpecification getRequest() {
        return REQ.get();
    }

    public static Response getResponse() {
        return RES.get();
    }

    public static void clear() {
        REQ.remove();
        RES.remove();
    }
}
