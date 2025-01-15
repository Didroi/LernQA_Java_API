import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FirstApiTest {

    @Test
    public void firstApiTest() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_text")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void secondApiTest() {

        Map<String, String > params = new HashMap<>();
        params.put("name", "Dmitrii");

        Response response = RestAssured
                .given()
//                .queryParam("name", "Dmitrii")
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .andReturn();
        response.prettyPrint();
    }

    @Test
    public void thirdApiTest() {

        Map<String, String > params = new HashMap<>();
        params.put("name", "Dmitrii");

        JsonPath response = RestAssured
                .given()
                .queryParams(params)
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String answer = response.get("answer");  // ответ  возвращается в json с одной парой ключ-значение, с ключем answer
        System.out.println(answer);
    }  // Гет с параметрами

    @Test
    public void forthApiTest() {

        Response response = RestAssured
                .given()
//                .body("param1=value1&param2=value2")  // способ1 отправки параметров в теле не гет-запроса
                .body("{\"param1\": \"value1\", \"param2\": \"value2\"}")  // способ2 отправки параметров в теле не гет-запроса
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        response.print();
    }  // передача параметров в теле

    @Test
    public void fifthApiTest() {

        Map<String, Object> payload = new HashMap<>();
        payload.put("param1", "value1");
        payload.put("param2", "value2");

        Response response = RestAssured
                .given()
                .body(payload)
                .post("https://playground.learnqa.ru/api/check_type")
                .andReturn();

        response.print();
    }  // передача параметров в теле Мапом

    @Test
    public void sixthApiTest() {

        Response response = RestAssured
//                .get("https://playground.learnqa.ru/api/check_type")
                .get("https://playground.learnqa.ru/api/get_500")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }  // Вывод статуса

    @Test
    public void seventhApiTest() {

        Response response = RestAssured
                .given()
                .redirects()
                .follow(false) // запрещаем редирект
                .when()
                .get("https://playground.learnqa.ru/api/get_303")
                .andReturn();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
    }  // не следовать редиректу, если вдруг
}
