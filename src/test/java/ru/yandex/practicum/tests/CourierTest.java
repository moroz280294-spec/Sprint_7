package ru.yandex.practicum.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.models.Courier;
import ru.yandex.practicum.steps.CourierSteps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CourierTest extends BaseTest {
    private Courier courier;
    private final CourierSteps courierSteps = new CourierSteps();

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        courier = new Courier();
        courier.withLogin(RandomStringUtils.randomAlphabetic(12))
                .withPassword(RandomStringUtils.randomAlphabetic(10))
                .withFirstName(RandomStringUtils.randomAlphabetic(11));
    }


    @Test
    @DisplayName("Тест.Успешное создание курьера")
    public void shouldCreateCourierTest() {
        courierSteps.createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Тест.Нельзя создать двух курьеров с одинаковым логином")
    public void shouldNotAllowDuplicateCourierLoginTest() {
        courierSteps.createCourier(courier).statusCode(201);
        Courier duplicate = new Courier()
                .withLogin(courier.getLogin())        //  тот же логин
                .withPassword("different_password")   //  другой пароль
                .withFirstName("Different Name");     //  другое имя
        courierSteps.createCourier(duplicate)
                .statusCode(409)
                .body("message", is("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Тест.Нельзя создать курьера без поля логин")
    public void shouldNotCreateCourierWithoutLoginTest() {
        courier.withLogin(null);
        courierSteps.createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Тест.Нельзя создать курьера без поля пароль")
    public void shouldNotCreateCourierWithoutPasswordTest() {
        courier.withPassword(null);
        courierSteps.createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Тест.Логин курьера")
    public void shouldLoginCourierTest() {
        courierSteps.createCourier(courier);
        courierSteps.loginCourier(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Тест.Логин курьера без поля логин")
    public void shouldLoginCourierWithoutLoginTest() {
        courierSteps.createCourier(courier);
        courier.withLogin(null);
        courierSteps.loginCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Тест.Логин курьера без поля пароль")
    public void shouldLoginCourierWithoutPasswordTest() {
        courierSteps.createCourier(courier);
        courier.withPassword(null);
        courierSteps.loginCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Тест.Логин курьера несуществующего курьера")
    public void shouldLoginCourierWithoutCreateCourierTest() {
        courierSteps.createCourier(courier);
        courier.withLogin(RandomStringUtils.randomAlphabetic(12));
        courierSteps.loginCourier(courier)
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }
    @After
    public void tearDown() {
        Integer id = courierSteps.loginCourier(courier).extract().path("id");
        if (id != null) {
            courierSteps.deleteCourier(new Courier().withId(id));
        }
    }

}

