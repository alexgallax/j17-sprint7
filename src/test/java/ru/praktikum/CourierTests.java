package ru.praktikum;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.courier.CourierClient;
import ru.praktikum.data.Courier;

import static org.junit.Assert.assertEquals;
import static ru.praktikum.data.CourierCreds.credsFrom;
import static ru.praktikum.courier.CourierGenerator.randomCourier;

public class CourierTests {

    private CourierClient courierClient;
    private Courier courier;
    private int courierId;

    @Before
    public void setUp() {
        courier = randomCourier();
        courierClient = new CourierClient();
    }

    @Test
    public void createCourier() {
        ValidatableResponse response = courierClient.create(courier);

        assertEquals("Статус код неверный при создании курьера",
                HttpStatus.SC_CREATED, response.extract().statusCode());

        ValidatableResponse loginResponse = courierClient.login(credsFrom(courier));
        courierId = loginResponse.extract().path("id");

        assertEquals("Статус код неверный при попытке логина созданным курьером",
                HttpStatus.SC_OK, loginResponse.extract().statusCode());
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }
}
