package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import models.BookModel;

import java.util.ArrayList;
import java.util.List;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.BookStoreApiSpecs.*;

public class ApiBookStore {
    @Step("Удаляем все книги в профиле")
    public Response deleteBooks(String userId, String token) {
        Response response = given(bookStoreRequestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userId)
                .when()
                .delete("/v1/Books")
                .then()
                .spec(userResponseSpecification204)
                .extract().response();
        return response;
    }
    @Step("Добавляем выбранную книгу в профиль")
    public  Response addBooks (String bookIsbn, String token,String userId) {
        BookModel getBookModel = new BookModel();
        BookModel.CollectionOfIsbns collection = new BookModel.CollectionOfIsbns();
        getBookModel.setUserId(userId);
        collection.setIsbn(bookIsbn);
        List<BookModel.CollectionOfIsbns> collections =new ArrayList<>();
        collections.add(collection);
        getBookModel.setCollectionOfIsbns(collections);
        Response response =given(bookStoreRequestSpec)
                .body(getBookModel)
                .filter(withCustomTemplates())
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/v1/Books")
                .then()
                .spec(userResponseSpecification201True)
                .extract().response();
        return response;
    }
    @Step("Удаляем выбранную книгу в профиле")
    public  Response deleteBook(String userId, String token, String bookIsbn){
        BookModel bookModel =new BookModel();
        bookModel.setUserId(userId);
        bookModel.setIsbn(bookIsbn);
        Response response =given(bookStoreRequestSpec)
                .body(bookModel)
                .header("Authorization","Bearer "+token)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(userResponseSpecification204)
                .extract().response();
        return response;
    }
}