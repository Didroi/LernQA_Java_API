import io.restassured.RestAssured;
import io.restassured.http.Headers;
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
    }  // Гет с параметрами + парсинг ответа

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

        response.prettyPrint();

        String locationHeader = response.getHeader("location");  // Смотрим Хедер Локейшен (куда был запланирован редирект)
        System.out.println("\nHeader location = " + locationHeader);
    }  // не следовать редиректу, если вдруг

    @Test
    public void eightApiTest() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Myheader1", "value1");
        headers.put("Myheader2", "value2");

        Response response = RestAssured
                .given()
                .headers(headers)
                .when()
                .get("https://playground.learnqa.ru/api/show_all_headers")
                .andReturn();

        response.prettyPrint();  // Эндпоинт в боди присылает полученные Хедеры (то есть хедеры запроса)

        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);  //  Хедеры ответа

    }  // Отправляем заголовки (хедеры)

    @Test
    public void ninthApiTest() {

        Map<String, String> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        System.out.println("\nPretty text:");
        response.prettyPrint();

        System.out.println("\nHeaders: ");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        System.out.println("\nCookies: ");
        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);

        String simpleResponseCookie = response.getCookie("auth_cookie");
        System.out.println("\nCoookies value: " + simpleResponseCookie);

    }  // Получаем куки

    @Test
    public void tenthApiTest() {

        Map<String, String> data = new HashMap<>();
        data.put("login", "secret_login");
        data.put("password", "secret_pass");

        Response responseForUse = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/api/get_auth_cookie")
                .andReturn();

        String responseCookie = responseForUse.getCookie("auth_cookie");  // кладем куку в переменную

        Map<String, String> cookies = new HashMap<>();  // создаем хешмэп из двух стринг
        if(responseCookie != null) {
            cookies.put("auth_cookie", responseCookie);  // кладем в хешмэп с ключем auth_cookie значение куки
        }

        Response responseForCheck = RestAssured
                .given()
                .body(data)
                .cookies(cookies)  // Используем куки
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();

        responseForCheck.print();

    }  // Используем куки


}
