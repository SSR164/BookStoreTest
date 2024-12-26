package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import models.BookModel;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ApiBookStore {
    @Step("Удаляем все книги в профиле")
    public static Response deleteBooks(String userId, String token) {
        Response response = given()
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " + token)
                .log().all()
                .contentType(JSON)
                .queryParam("UserId", userId)
                .when()
                .delete("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .log().status()
                .statusCode(204)
                .extract().response();
        return response;
    }
    @Step("Добавляем выбранную книгу в профиль")
    public static Response addBooks (String bookIsbn, String token,String userId) {
        BookModel getBookModel = new BookModel();
        BookModel.CollectionOfIsbns collection = new BookModel.CollectionOfIsbns();
        getBookModel.setUserId(userId);
        collection.setIsbn(bookIsbn);
        List<BookModel.CollectionOfIsbns> collections =new ArrayList<>();
        collections.add(collection);
        getBookModel.setCollectionOfIsbns(collections);
        Response response =given()
                .body(getBookModel)
                .filter(new AllureRestAssured())
                .header("Authorization", "Bearer " + token)
                .log().all()
                .contentType(JSON)
                .when()
                .post("https://demoqa.com/BookStore/v1/Books")
                .then()
                .log().body()
                .log().status()
                .statusCode(201)
                .extract().response();
        return response;
    }
    @Step("Удаляем выбранную книгу в профиле")
    public static Response deleteBook(String userId, String token, String bookIsbn){
        BookModel bookModel =new BookModel();
        bookModel.setUserId(userId);
        bookModel.setIsbn(bookIsbn);
        Response response =given()
                .body(bookModel)
                .filter(new AllureRestAssured())
                .header("Authorization","Bearer "+token)
                .log().all()
                .contentType(JSON)
                .when()
                .delete("https://demoqa.com/BookStore/v1/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204)
                .extract().response();
        return response;
    }
}