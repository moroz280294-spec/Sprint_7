package ru.yandex.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.models.Orders;

import static config.RestConfig.ORDERS;
import static io.restassured.RestAssured.given;

public class OrdersSteps {
    @Step("Создание заказа")
    public ValidatableResponse createOrders(Orders orders) {
        return given()
                .body(orders)
                .when()
                .post(ORDERS)
                .then();
    }

    @Step("Отмена заказа по трек-номеру")
    public ValidatableResponse cancelOrder(Integer trackNumber) {
        return given()
                .queryParam("track", trackNumber)  // ← передаём только число
                .when()
                .put("/api/v1/orders/cancel")
                .then();
    }
}
