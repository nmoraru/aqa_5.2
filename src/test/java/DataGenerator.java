import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Data;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Data
public class DataGenerator {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void makeRegistration(RegistrationDto RegistrationDto) {
        given()
                .spec(requestSpec)
                .body(RegistrationDto)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static RegistrationDto generateValidActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        RegistrationDto validUser = new RegistrationDto(
                faker.name().firstName().toLowerCase(),
                faker.internet().password(),
                "active");
        makeRegistration(validUser);
        return validUser;
    }

    public static RegistrationDto generateValidBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        makeRegistration(new RegistrationDto(login, password, "blocked"));
        return new RegistrationDto(login, password, "blocked");
    }

    public static RegistrationDto generateActiveUserInvalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        String loginOne = faker.name().firstName().toLowerCase();
        String loginTwo = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        String status = "active";
        makeRegistration(new RegistrationDto(loginOne, password, status));
        return new RegistrationDto(loginTwo, password, status);
    }

    public static RegistrationDto generateActiveUserInvalidPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName().toLowerCase();
        String passwordOne = faker.internet().password();
        String passwordTwo = faker.internet().password();
        String status = "active";
        makeRegistration(new RegistrationDto(login, passwordOne, status));
        return new RegistrationDto(login, passwordTwo, status);
    }

    public static RegistrationDto generateBlockedUserInvalidLogin() {
        Faker faker = new Faker(new Locale("en"));
        String loginOne = faker.name().firstName().toLowerCase();
        String loginTwo = faker.name().firstName().toLowerCase();
        String password = faker.internet().password();
        String status = "blocked";
        makeRegistration(new RegistrationDto(loginOne, password, status));
        return new RegistrationDto(loginTwo, password, status);
    }

    public static RegistrationDto generateBlockedUserInvalidPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName().toLowerCase();
        String passwordOne = faker.internet().password();
        String passwordTwo = faker.internet().password();
        String status = "blocked";
        makeRegistration(new RegistrationDto(login, passwordOne, status));
        return new RegistrationDto(login, passwordTwo, status);
    }

}
