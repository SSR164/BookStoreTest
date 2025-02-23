package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class ApiSpecs {

    public static RequestSpecification RequestSpec=with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON);


    public static ResponseSpecification createResponseSpecification(int statusCode, boolean expectJson ) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(STATUS)
                .log(BODY);

        if (expectJson) {
            builder.expectContentType(JSON);
        }

        return builder.build();
    }

    public static final ResponseSpecification userResponseSpecification200 = createResponseSpecification(200,false);
    public static final ResponseSpecification userResponseSpecification201True = createResponseSpecification(201,true);
    public static final ResponseSpecification userResponseSpecification204 = createResponseSpecification(204,false);
}