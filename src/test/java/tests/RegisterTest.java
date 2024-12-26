package tests;

import PageObject.PoAccount;
import api.ApiAccount;
import PageObject.PoLogin;
import api.ApiBookStore;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

@Tag("bookStore")
public class RegisterTest extends TestBase {
    ApiAccount apiAccount = new ApiAccount();
    ApiBookStore apiBookStore = new ApiBookStore();
    PoLogin poLogin = new PoLogin();
    PoAccount poAccount = new PoAccount();
    String password = "Test@12345";
    String userName = "TestSer";
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
        Response responseBookStoreDeleteBooks = apiBookStore.deleteBooks(userId, token);
        Response responseBookStoreAddBookGit = apiBookStore.addBooks(bookGitIsbn, token, userId);
        Response responseBookStoreAddBookJS = apiBookStore.addBooks(bookJsIsbn, token, userId);
        Response responseGetUserBooks = apiAccount.getUserBooks(token, userId);
        responseGetUserBooks.then().body("books.isbn", hasItems(bookGitIsbn, bookJsIsbn));
        Response responseBookStoreDeleteBook = apiBookStore.deleteBook(userId, token, bookJsIsbn);
        poLogin.loginPageRegisteredPerson(userId, expires, token);
        poAccount.openAccount();
        poAccount.checkBook(bookGitName);
        poAccount.checkNotBook(bookJsName);


    }
}