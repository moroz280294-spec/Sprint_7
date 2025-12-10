import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateCourier {
    @Test

    public void shouldCreateCourierTest() {
        String login = RandomStringUtils.randomAlphabetic(12);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(11);
        given()
                .baseUri( "https://qa-scooter.praktikum-services.ru/")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"login\": \"" + login + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"firstName\": \"" + firstName + "\"\n" +
                        "}")
            .when()
            .post("/api/v1/courier")
            .then()
                .statusCode(201)
                .and()
                .body("ok",is(true));
}
    @Test

    public void shouldLoginCourierTest() {
        public void shouldCreateCourierTest() {
            String login = RandomStringUtils.randomAlphabetic(12);
            String password = RandomStringUtils.randomAlphabetic(10);
            String firstName = RandomStringUtils.randomAlphabetic(11);
            given()
                    .baseUri( "https://qa-scooter.praktikum-services.ru/")
                    .contentType(ContentType.JSON)
                    .body("{\n" +
                            "  \"login\": \"" + login + "\",\n" +
                            "  \"password\": \"" + password + "\",\n" +
                            "  \"firstName\": \"" + firstName + "\"\n" +
                            "}")
                    .when()
                    .post("/api/v1/courier")
                    .then()
                    .statusCode(201)
                    .and()
                    .body("ok",is(true));
        }
        given()
                .baseUri( "https://qa-scooter.praktikum-services.ru/")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"login\": \"" + login + "\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "}"))
                .when()
                .post("/api/v1/courier/login)
                .then()
                .statusCode(201)
                        .body("id",notNullValue());
    }
}

