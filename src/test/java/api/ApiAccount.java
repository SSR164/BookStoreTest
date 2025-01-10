package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.LombokModelAccount;
import static io.restassured.RestAssured.given;
import static specs.AccountApiSpecs.*;


public class ApiAccount {

    @Step("Логинемся через API")
    public  Response loging (String userName, String password ){
        LombokModelAccount lombokModelLogin=new LombokModelAccount();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);

        Response response =given(accountRequestSpec)
                    .body(lombokModelLogin)
                .when()
                    .post("/v1/Login")
                .then()
                    .spec(accountResponseSpec)
                    .extract().response();
        return response;
    }
    @Step("Получаем список книг в профиле")
    public  Response getUserBooks (String token, String userId ){
        Response response =given(accountRequestSpec)
                .header("Authorization","Bearer "+token)
                .when()
                .get("/v1/User/"+userId)
                .then()
                .spec(accountResponseSpec)
                .extract().response();
        return response;

    }
}
