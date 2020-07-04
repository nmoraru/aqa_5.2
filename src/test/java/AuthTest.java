import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @Test
    void shouldActiveUserValidLoginAndPass() {
        RegistrationDto user = DataGenerator.generateValidActiveUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login] ").click();
        $(byText("Личный кабинет")).waitUntil(Condition.visible, 5000);
    }

    @Test
    void shouldActiveUserAndPasswordInvalid() {
        RegistrationDto user = DataGenerator.generateActiveUserInvalidPassword();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login] ").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }

    @Test
    void shouldActiveUserAndLoginInvalid() {
        RegistrationDto user = DataGenerator.generateActiveUserInvalidLogin();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login] ").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }

    @Test
    void shouldBlockedUserAndValidLoginAndPass() {
        RegistrationDto user = DataGenerator.generateValidBlockedUser();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login] ").click();
        $(byText("Пользователь заблокирован")).waitUntil(Condition.visible, 5000);
    }

    @Test
    void shouldBlockedUserAndPasswordInvalid() {
        RegistrationDto user = DataGenerator.generateBlockedUserInvalidPassword();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login] ").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }

    @Test
    void shouldBlockedUserAndLoginInvalid() {
        RegistrationDto user = DataGenerator.generateBlockedUserInvalidLogin();
        open("http://localhost:9999");
        $("[data-test-id=login] input").sendKeys(user.getLogin());
        $("[data-test-id=password] input").sendKeys(user.getPassword());
        $("[data-test-id=action-login] ").click();
        $(byText("Неверно указан логин или пароль")).waitUntil(Condition.visible, 5000);
    }

}
