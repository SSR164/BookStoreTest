package PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PoLogin {
    @Step("Открываем техническую страницу  Логинимся на странице ")
    public PoLogin loginPageRegisteredPerson(String useriD, String expires, String token){
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID",useriD));
        getWebDriver().manage().addCookie(new Cookie("expires",expires));
        getWebDriver().manage().addCookie(new Cookie("token",token));
        return this;
    }
}
