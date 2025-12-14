package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.models.Orders;
import ru.yandex.practicum.steps.OrdersSteps;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest {
    private final String[] colorInput;
    private Orders order;
    private Integer track;
    private final OrdersSteps ordersSteps = new OrdersSteps();

    public CreateOrderTest(String[] colorInput) {
        this.colorInput = colorInput;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][] {
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK","GREY"}},
                {new String[]{""}},
        };
    }

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        order = new Orders()
                .withFirstNameClient(RandomStringUtils.randomAlphabetic(8))
                .withLastName(RandomStringUtils.randomAlphabetic(10))
                .withAddress("г. Москва, ул. " + RandomStringUtils.randomAlphabetic(7))
                .withMetroStation("4")
                .withPhone("+7" + RandomStringUtils.randomNumeric(10))
                .withRentTime(5)
                .withDeliveryDate("2025-12-15")
                .withComment(RandomStringUtils.randomAlphabetic(20));


        if (colorInput != null) {
            order.withColor(colorInput);
        }

    }

    @Test
    @DisplayName("Создание заказа с разными вариантами цвета")
    public void shouldCreateOrderWithColorTest() {
        track = ordersSteps.createOrders(order)
                .extract()
                .path("track");

        ordersSteps.createOrders(order)
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

