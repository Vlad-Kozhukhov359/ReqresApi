package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserCredetials;
import models.UserModelResponse;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Работа с данными пользователей")
@Feature("Создание пользователя")
public class CreateUserTest {

    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Создание конкретного пользователя")
    @Description("Создаем только имя")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCreateUserWithoutJob() throws JsonProcessingException {
        UserCredetials user = new UserCredetials("morpheus");

        step("Отправка POST-запроса");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201) // Проверяем, что статус код 201
                .extract()
                .response();

        step("Десериализация JSON-ответа в обект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");


        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
    }
    @Story("Создание конкретного пользователя")
    @Description("Создаем только работу")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCreateUserWithoutName() throws JsonProcessingException {
        UserCredetials user = new UserCredetials(null, "leader");

        step("Отправка POST-запроса");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201) // Проверяем, что статус код 201
                .extract()
                .response();

        step("Десериализация JSON-ответа в обект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        assertEquals(user.getJob(), userModelResponse.getJob(), "Профессия пользователя не совпадает");
    }
    @Story("Создание конкретного пользователя")
    @Description("Создаем имя и работу")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void WithNameAndJob() throws JsonProcessingException {
        UserCredetials user = new UserCredetials("morpheus", "leader");

        step("Отправка POST-запроса");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201) // Проверяем, что статус код 201
                .extract()
                .response();

        step("Десериализация JSON-ответа в обект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        // Для более понятного просмотра того, что отправили и получили
        System.out.println("Отправлено имя: " + user.getName());
        System.out.println("Отправлено работа: " + user.getJob());
        System.out.println("Получено имя: " + userModelResponse.getName());
        System.out.println("Получена работа: " + userModelResponse.getJob());

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Работа пользователя не совпадает");
    }
}