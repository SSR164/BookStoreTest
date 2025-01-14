package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.AccountModel;
import static io.restassured.RestAssured.given;
import static specs.ApiSpecs.accountRequestSpec;
import static specs.ApiSpecs.userResponseSpecification200;


public class AccountApi {

    @Step("Логинемся через API")
    public  Response loging (String userName, String password ){
        AccountModel lombokModelLogin=new AccountModel();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);

        Response response =given(accountRequestSpec)
                    .body(lombokModelLogin)
                .when()
                    .post("/v1/Login")
                .then()
                    .spec(userResponseSpecification200)
                    .extract().response();
        return response;
    }
    @Step("Получаем список книг в профиле используя API")
    public  Response getUserBooks (String token, String userId ){
        Response response =given(accountRequestSpec)
                .header("Authorization","Bearer "+token)
                .when()
                .get("/v1/User/"+userId)
                .then()
                .spec(userResponseSpecification200)
                .extract().response();
        return response;

    }
}
