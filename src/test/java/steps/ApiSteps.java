package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.LoggerHelper;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class ApiSteps {

    private static final Logger log = LoggerFactory.getLogger(ApiSteps.class);

    RequestSpecBuilder reqSpec;
    RequestSpecification req;
    Response res;
    String endpoint, root;
    JsonPath json;

    @Given("^basepath \"(.*)\"$")
    public void setBasepath(String basePath) {
        reqSpec = new RequestSpecBuilder().
                setBaseUri(basePath).
                addFilter(new LoggerHelper());
    }

    @And("^calling loginAPI with username \"(.*)\" to store the Authorization token$")
    public void callingLoginAPIWithUsernameToStoreAuthorizatonToken(String userName) {
        String token =
                given().
                baseUri("https://www.advantageonlineshopping.com").
                header("Content-Type","text/xml").
                when().
                body(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"> " +
                "<soap:Body>" +
                "<AccountLoginRequest xmlns=\"com.advantage.online.store.accountservice\">" +
                "<loginPassword>Pass123</loginPassword>" +
                "<loginUser>"+ userName + "</loginUser>" +
                "</AccountLoginRequest>" +
                "</soap:Body>" +
                "</soap:Envelope>"
                ).
                post("/accountservice/ws/AccountLoginRequest").
                path("Envelope.Body.AccountLoginResponse.StatusMessage.t_authorization");
        reqSpec.addHeader("Authorization", "Basic" + token);
    }

    @Given("^the endpoint \"(.*)\"$")
    public void theEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @And("^creating a (SOAP|REST) request$")
    public void setContentType(String service) {
        if(service.equals("SOAP")) {
            reqSpec.
                    addHeader("Content-Type", "text/xml");
        } else {
            reqSpec.
                    addHeader("Content-Type", "application/json");
        }
        req = given().spec(reqSpec.build());
    }

    @When("^setting \"(.*)\" header with value (.*)$")
    public void settingHeadersWithValues(String header, String value) {
        req.
                header(header, value);
    }

    @When("^setting the following header(?:s)?:$")
    public void settingTheFollowingHeaders(List<Map<String, String>> headers) {
        for(Map<String, String> header : headers) {
            req.
                    header(header.get("key"), header.get("value"));
        }

    }

    @When("^setting the value \"(.*)\" in path variable \"(.*)\"$")
    public void settingTheValueInPathVariable(String value, String key) {
        req.
                pathParam(key, value);
    }

    @When("^setting the request body$")
    public void withBody(String reqBody) {
        req.
                body(reqBody);
    }

    @When("^sending a (GET|POST|PUT|DELETE) request$")
    public void sendingRequest(String verb) {
        switch(verb) {
            case "GET":
                res = req.
                        when().
                        get(endpoint);
                break;
            case "POST":
                res = req.
                        when().
                        post(endpoint);
                break;
            case "PUT":
                res = req.
                        when().
                        put(endpoint);
                break;
            case "DELETE":
                res = req.
                        when().
                        delete(endpoint);
                break;
        }
    }

    @Then("^the status code should be (\\d{3})$")
    public void isStatusCode(int statusCode) {
        String response = res.then().
                statusCode(statusCode).extract().asString();
        Assert.assertEquals(res.statusCode(), statusCode);
    }

    @And("^the field \"(.*)\" in the response body should be a non empty array$")
    public void theResponseBodyFieldValueShouldBeANonEmptyArray(String field) {
        res.then().
                body(field, instanceOf(List.class)).body(field, Matchers.not(emptyArray()));
    }

    @And("^setting the response body root to \"(.*)\"$")
    public void settingRequestBodyRoot(String root) {
        this.root = root;

    }

    @And("^the field \"(.*)\" in the response body should have the value \"(.*)\"(?: of type (.*))?$")
    public void isResponseBodyFieldValue(String field, String value, String type) {
        if(res.header("Content-Type").contains("xml")) {
            res.then().
                    body(root + "." + field, equalTo(value));
        } else {
            if(type == null || type.equals("string"))
                res.then().
                        body(field, equalTo(value));
            else if(type.equals("boolean"))
                res.then().
                        body(field, equalTo(Boolean.parseBoolean(value)));
            else
                res.then().
                        body(field, equalTo(Integer.parseInt(value)));
        }
    }

    @And("^the response token should be printed$")
    public void printResponseField() {
        String response = res.then().extract().response().asString();
        XmlPath xml = new XmlPath(response);
        xml.setRootPath("Envelope.Body.AccountLoginResponse.StatusMessage");
        System.out.println(xml.getString("token"));
    }

    @And("^the response matches the json response schema \"(.*)\"$")
    public void theJsonSchemaMatches(String schemaLocation) {
        res.then().
                body(matchesJsonSchemaInClasspath(schemaLocation));
    }

    @And("^the field \"(.*)\" values in the response array should be:$")
    public void fieldValuesInTheArrayShouldBe(String field, List<String> values) {
        res.then().
                body(field, hasItems(values.toArray(new String[0])));
    }
}
