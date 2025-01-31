package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;

public class ApiCoreRequests {

    @Step("Make a GET-request with token and auth cookie")
    public Response makeGetRequest(String url, String token, String cookie) {
        return  given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
    }
}
