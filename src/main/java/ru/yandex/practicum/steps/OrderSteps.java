package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static config.RestConfig.ORDERS_LIST;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step("Получение списка заказов")
    public static ValidatableResponse getOrderList() {
        return given()
                .when()
                .get(ORDERS_LIST)
                .then();
    }
}
