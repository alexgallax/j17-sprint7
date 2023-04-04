package ru.praktikum.courier;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.data.Courier;
import ru.praktikum.data.CourierCreds;

import static io.restassured.RestAssured.given;

public class CourierClient {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    private static final String PATH = "api/v1/courier";
    private static final String LOGIN_PATH = "api/v1/courier/login";

    public CourierClient() {
        RestAssured.baseURI = BASE_URI;
    }

    public ValidatableResponse create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(PATH)
                .then();
    }

    public ValidatableResponse login(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    public void delete(int courierId) {

    }
}
