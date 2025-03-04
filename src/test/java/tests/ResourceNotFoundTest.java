package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceNotFoundTest {

    private final String BASE_URL = "https://reqres.in/api/unknown";


    @Test
    public void testResourceNotFound() throws Exception {

        // Отправка гет-запроса на получение несуществующего пользователя (например id 7777)
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/7777")
                .then()
                .statusCode(404) // Проверяем, что статус код 404 not Found
                .extract()
                .response();


        // Проверяем, что тело ответа пустое
        String responseBody = response.getBody().asString();
        assertEquals("{}", responseBody, "Тел ответа не является пустым");
    }
}