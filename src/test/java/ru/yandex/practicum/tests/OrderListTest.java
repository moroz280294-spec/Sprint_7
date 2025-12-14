package ru.yandex.practicum.tests;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.steps.OrderListSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest extends BaseTest {
    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    @DisplayName("Тест. Получение списка заказов")
    public void shouldReturnOrderListTest() {

        OrderListSteps.getOrderList()
                .statusCode(200)
                .body("orders", notNullValue()) ;
    }


}