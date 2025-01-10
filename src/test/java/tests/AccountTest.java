package tests;


import config.ConfigBookStore;
import pages.PoAccount;

import api.ApiAccount;
import pages.PoLogin;
import api.ApiBookStore;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.Matchers.*;

@Tag("bookStore")
public class AccountTest extends TestBase {
    ApiAccount apiAccount = new ApiAccount();
    ApiBookStore apiBookStore = new ApiBookStore();
    PoLogin poLogin = new PoLogin();
    PoAccount poAccount = new PoAccount();
    ConfigBookStore configBookStore =new ConfigBookStore ();
    String password =configBookStore.getPassword();
    String userName = configBookStore.getUserName();
    String bookGitIsbn = "9781449325862";
    String bookJsIsbn = "9781449331818";
    String bookGitName = "Git Pocket Guide";
    String bookJsName = "Learning JavaScript Design Patterns";



    @DisplayName("UI проверка удаления одной книге в профиле")
    @Test
    void deletBookUiTest() {
        Response responseapiAccount = apiAccount.loging(userName, password);
        String userId = responseapiAccount.path("userId");
        String token = responseapiAccount.path("token");
        String expires = responseapiAccount.path("expires");
        apiBookStore.deleteBooks(userId, token);
        apiBookStore.addBooks(bookJsIsbn , token, userId);
        apiBookStore.addBooks( bookGitIsbn, token, userId);
        Response responseGetUserBooks = apiAccount.getUserBooks(token, userId);
        responseGetUserBooks.then().body("books.isbn", hasItems(bookGitIsbn, bookJsIsbn));
        poLogin.loginPageRegisteredPerson(userId, expires, token);
        poAccount.openAccount();
        poAccount.checkUserName(userName);
        poAccount.checkBook(bookGitName);
        poAccount.checkBook(bookJsName);
        poAccount.deleteBook();
        poAccount.deleteBookOk();
        poAccount.checkBook(bookGitName);
        poAccount.checkNotBook(bookJsName);


    }
}