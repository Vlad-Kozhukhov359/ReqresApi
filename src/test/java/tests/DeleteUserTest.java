package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Работа с данными пользователей")
@Feature("Удаление данных пользователя")
public class DeleteUserTest {

    private final String BASE_URL = "https://reqres.in/api/users";

    @Story("Проверка, что пользователь удален")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testUserDelete() throws Exception {

        // Отправка delete запроса
        Response response = RestAssured
                .given()
                .when()
                .delete(BASE_URL + "/2")
                .then()
                .statusCode(204) // Проверяем, что статус код 204
                .extract()
                .response();


        // Проверяем, что тело ответа пустое
        String responseBody = response.getBody().asString();
        assertTrue(responseBody.isEmpty(), "Тело ответа должно быть пустым");
    }
}
