package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleUserResponse;
import models.UserData;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Работа с данными пользователей")
@Feature("Получает информацию о пользователях")
public class GetSingleUserTest {

    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение информации о конкретном пользователе")
    @Description("Получение информации о конкретном пользователе, а точнее всех его данных включая аватарку")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testGetSingleUser() throws Exception {

        step("Отправка гет-запроса");
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200) // Проверяем, что статус код 200
                .extract()
                .response();

        step("Десериализация JSON-ответа в обект SingleUserResponse");
        SingleUserResponse singleUserResponse = objectMapper.readValue(response.asString(), SingleUserResponse.class);

        step("Переменная хранящая пользователя");
        UserData user = singleUserResponse.getData();

        step("Переменная хранящая ответ при проверке");
        String errorMessageForSingleUser = "Email пользователя " + user.getId() + " не оканчивается на @reqres.in";

        step("Проверяем, что email  пользователя заканчивается на @reqres.in");
        assertTrue(user.getEmail().endsWith("@reqres.in"), errorMessageForSingleUser);
    }
}
