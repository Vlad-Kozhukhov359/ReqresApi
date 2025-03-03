package tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserDataListResource;
import models.UsersResponseListResource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetListResourceTest {

    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetListResource() throws Exception {

        // Отправка гет-запроса
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200) // Проверяем, что статус код 200
                .extract()
                .response();

        // Десериализация JSON-ответа в обект UsersResponseListResource
        UsersResponseListResource usersResponse = objectMapper.readValue(response.asString(), UsersResponseListResource.class);

        // Проверяем что количество пользователей согласно документации Reqres)
        assertEquals(1, usersResponse.getPage(), "Неверная страница");
        assertEquals(6, usersResponse.getPer_page(), "Неверное количество в per_page");
        assertEquals(12, usersResponse.getTotal(), "Неверное количество страниц");
        assertEquals(2, usersResponse.getTotal_pages(), "Неверное общее количество страниц");
        assertEquals(6, usersResponse.getData().size(), "Количество пользователей не совпадает");


        for (UserDataListResource user : usersResponse.getData()) {
            // Проверяем, что год должен быть в диапазоне от 2000 до 2005
            assertTrue(user.getYear() >= 2000 && user.getYear() <= 2005, "Год " + user.getYear() + " должен быть в диапазоне от 2000 до 2005");

            // Проверка, что имя не пустое
            assertNotNull(user.getName(), "Имя пользователя не должно быть null");
            assertTrue(!user.getName().isEmpty(), "Имя пользователя не должно быть пустым");

        }
    }
}
