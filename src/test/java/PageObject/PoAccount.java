package PageObject;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;

public class PoAccount {
    private SelenideElement booksPanel = $(".profile-wrapper");

    @Step("Открываем страницу с Аккаунтом ")
    public PoAccount openAccount(){
        open("/profile");
        return this;
    }
    @Step("Проверяем что книги Есть в корзине ")
        public PoAccount checkBook(String book){
           booksPanel.shouldHave(text(book));
            return this;
        }
    @Step("Проверяем что книги НЕТ в корзине ")
    public PoAccount checkNotBook(String book){
        booksPanel.shouldNotHave(text(book));
        return this;
    }
}
