package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LoggerHelper implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LoggerHelper.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext ctx) {
        log.info("----- REQUEST -----");
        log.info("Method: {}", requestSpec.getMethod());
        log.info("URI: {}", requestSpec.getURI());
        log.info("Headers: {}", requestSpec.getHeaders());
        log.info("Body: {}", Optional.ofNullable(requestSpec.getBody()));

        Response response = ctx.next(requestSpec, responseSpec);

        log.info("----- RESPONSE -----");
        log.info("Status Code: {}", response.getStatusCode());
        log.info("Headers: {}", response.getHeaders());
        log.info("Body: {}", response.getBody().asPrettyString());

        return response;
    }


}
