package tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.Map;
public class TestBase {


    @BeforeAll
    static void beforeAll() {
        System.out.println("USERNAME: " + System.getProperty("USERNAME"));
        System.out.println("PASSWORD: " + System.getProperty("PASSWORD"));
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.remote = System.getProperty("remoteUrl");
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.baseURI="https://demoqa.com";
        RestAssured.authentication = RestAssured.basic(
                System.getProperty("userName", "defaultUser"),
                System.getProperty("password", "defaultPass")
        );





        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;


    }
    @BeforeEach
     void beforeEach() {
        // Логирование для Allure
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }



    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

@AfterEach
void afterEach() {
    Selenide.closeWebDriver();
}
}
