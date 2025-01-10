package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class PoAccount {
    private SelenideElement booksPanel = $(".profile-wrapper"),
            accountPanel = $(".body-height"),
    //accountPanel = $(".body-height"),row
            closeSmallModalOk=$("#closeSmallModal-ok"),
            deleteBook=$(".profile-wrapper").$("#delete-record-undefined");

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
    @Step("Проверяем авторизованный профиль ")
    public PoAccount checkUserName(String userName){
        accountPanel.shouldNotHave(text(userName));
        return this;
    }
    @Step("Удаляем 1 книгу через UI ")
    public PoAccount deleteBook(){
        deleteBook.click();
        return this;
    }
    @Step("Подтверждаем удаление книги ")
    public PoAccount deleteBookOk(){
        closeSmallModalOk.click();
        return this;
    }
}
