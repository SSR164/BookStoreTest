package tests;


import config.BookStoreConfig;
import pages.AccountPage;

import api.AccountApi;
import pages.LoginPage;
import api.BookStoreApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.Matchers.*;

@Tag("bookStore")
public class AccountTest extends TestBase {
    AccountApi apiAccount = new AccountApi();
    BookStoreApi apiBookStore = new BookStoreApi();
    LoginPage poLogin = new LoginPage();
    AccountPage poAccount = new AccountPage();
    BookStoreConfig bookStoreConfig =new BookStoreConfig();
    String userName = bookStoreConfig.getUserName();
    String password = bookStoreConfig.getPassword();
    String bookGitIsbn = "9781449325862";
    String bookJsIsbn = "9781449331818";
    public String  bookGitName = "Git Pocket Guide";
    String bookJsName = "Learning JavaScript Design Patterns";



    @DisplayName("UI проверка удаления одной книге в профиле")
    @Test
    void deletBookUiTest() {
        Response responseapiAccount = apiAccount.loging(userName, password);
        System.out.println("Response: " + responseapiAccount.prettyPrint());
        System.out.println("Username: " + System.getProperty("userName"));
        System.out.println("Password: " + System.getProperty("password"));
        String userId = responseapiAccount.path("userId");
        String token = responseapiAccount.path("token");
        String expires = responseapiAccount.path("expires");
        apiBookStore.deleteBooks(userId, token);
        apiBookStore.addBooks(bookJsName,bookJsIsbn , token, userId);
        apiBookStore.addBooks( bookGitName,bookGitIsbn, token, userId);
        Response responseGetUserBooks = apiAccount.getUserBooks(token, userId);
        responseGetUserBooks.then().body("books.isbn", hasItems(bookGitIsbn, bookJsIsbn));
        poLogin.loginPageRegisteredPerson(userId, expires, token);
        poAccount.openAccount();
        poAccount.checkUserName(userName);
        poAccount.checkBook(bookGitName);
        poAccount.checkBook(bookJsName);
        poAccount.deleteBook(bookJsName);
        poAccount.deleteBookOk(bookJsName);
        poAccount.checkBook(bookGitName);
        poAccount.checkNotBook( bookJsName);


    }
}