package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.models.Orders;
import ru.yandex.practicum.steps.OrdersSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateOrderTest extends BaseTest {
    private Orders orders;
    private Integer track; // ← добавь это поле
    private final OrdersSteps ordersSteps = new OrdersSteps();


    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        orders = new Orders();
        orders.setFirstNameClient(RandomStringUtils.randomAlphabetic(8));
        orders.setLastName(RandomStringUtils.randomAlphabetic(10));
        orders.setAddress("г. " + RandomStringUtils.randomAlphabetic(6) + ", ул. " + RandomStringUtils.randomAlphabetic(7) + " д. " + RandomStringUtils.randomNumeric(2));
        orders.setMetroStation("4");
        orders.setPhone("+7" + RandomStringUtils.randomNumeric(10));
        orders.setRentTime(5);
        orders.setDeliveryDate("2025-12-15");
        orders.setComment(RandomStringUtils.randomAlphabetic(20));
        orders.setColor(new String[]{"BLACK"});
    }


    @Test
    @DisplayName("Тест. Успешное создание заказа")
    public void shouldCreateOrderTest() {
        track = ordersSteps.createOrders(orders).extract().path("track");
        ordersSteps.createOrders(orders)
                .statusCode(201)
                .body("track", notNullValue());
    }

    @After
    public void tearDown() {
        if (track != null) {
            ordersSteps.cancelOrder(track);
        }
    }
}

