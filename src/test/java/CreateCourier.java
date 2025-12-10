import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CreateCourier {
    @Test

    public void get() {
        String login = RandomStringUtils.randomAlphabetic(12);

        given()
                .baseUri( "https://qa-scooter.praktikum-services.ru/")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"login\": \"" + login + "\",\n" +
                        "  \"password\": \"1234\",\n" +
                        "  \"firstName\": \"saske\"\n" +
                        "}")
            .when()
            .post("/api/v1/courier")
            .then()
                .statusCode(201)
                .and()
                .body("ok",is(true));
}}

