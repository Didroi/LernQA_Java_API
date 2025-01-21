package archiv;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseCase;
import org.junit.jupiter.api.Test;

public class TestHWHeaders extends BaseCase {

    @Test
    public void checkHeadersTest() {

        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_header")
                .andReturn();


        String secretHeaderKey = "x-secret-homework-header";
        String secretHeaderValue = "Some secret value";
//
        Assertions.assertHeaderHasSecretData(this.getHeader(response), secretHeaderKey, secretHeaderValue);

    }
}
