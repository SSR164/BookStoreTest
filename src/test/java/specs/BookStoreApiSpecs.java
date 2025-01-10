package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class BookStoreApiSpecs {
    public static RequestSpecification bookStoreRequestSpec=with()
        .filter(withCustomTemplates())
        .log().all()
        .contentType(JSON)
        .baseUri("https://demoqa.com/BookStore");

    public static ResponseSpecification createResponseSpecification(int statusCode ) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(STATUS)
                .log(BODY);


        return builder.build();
    }

    public static final ResponseSpecification userResponseSpecification201 = createResponseSpecification(201);
    public static final ResponseSpecification userResponseSpecification204 = createResponseSpecification(204);
}