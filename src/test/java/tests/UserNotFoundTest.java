package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Работа с данными пользователей")
@Feature("Получает информацию о пользователях")
public class UserNotFoundTest {

    private final String BASE_URL = "https://reqres.in/api/users";

    @Story("Проверка получения данных несуществующего пользователя")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testUserNotFound() throws Exception {

        // Отправка гет-запроса на получение несуществующего пользователя (например id 9999)
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/9999")
                .then()
                .statusCode(404) // Проверяем, что статус код 404 not Found
                .extract()
                .response();


        // Проверяем, что тело ответа пустое
        String responseBody = response.getBody().asString();
        assertEquals("{}", responseBody, "Тел ответа не является пустым");
    }
}
