package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import models.LombokModelAccount;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasItems;


public class ApiAccount {

    @Step("Логинемся через API")
    public static Response loging (String userName, String password ){
        LombokModelAccount lombokModelLogin=new LombokModelAccount();
        lombokModelLogin.setUserName(userName);
        lombokModelLogin.setPassword(password);

        Response response =given()
                .body(lombokModelLogin)
                .contentType(JSON)
                .filter(new AllureRestAssured())
                .log().all()
                .when()
                .post("https://demoqa.com/Account/v1/Login")
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .extract().response();
        return response;
    }
    @Step("Получаем список книг в профиле")
    public static Response getUserBooks (String token, String userId ){
        Response response =given()
                .header("Authorization","Bearer "+token)
                .filter(new AllureRestAssured())
                .log().all()
                .contentType(JSON)
                .when()
                .get("https://demoqa.com/Account/v1/User/"+userId)
                .then()
                .log().body()
                .log().status()
                .statusCode(200)
                .extract().response();
        return response;

    }
}
