package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleResourceResponse;
import models.UserDataListResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Работа с данными пользователей")
@Feature("Получает информацию о пользователях")
public class GetSingleResourceTest {

    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение информации о конкретном пользователе")
    @Test
    public void testGetSingleResource() throws Exception {

        // Отправка гет-запроса
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200) // Проверяем, что статус код 200
                .extract()
                .response();

        // Десериализация JSON-ответа в обект SingleResourceResponse
        SingleResourceResponse singleResourceResponse = objectMapper.readValue(response.asString(), SingleResourceResponse.class);

        // Переменная хранящая пользователя
        UserDataListResource user = singleResourceResponse.getData();

        // Проверка, что имя не пустое
        assertNotNull(user.getName(), "Имя пользователя не должно быть null");
        assertTrue(!user.getName().isEmpty(), "Имя пользователя не должно быть пустым");

    }
}
