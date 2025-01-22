package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import lib.BaseCase;
import lib.UserAgent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

public class TestHWUserAgent extends BaseCase {

    /*
        {
        "user_agent":"Mozilla\\/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit\\/537.36 (KHTML, like Gecko) Chrome\\/131.0.0.0 Safari\\/537.36",
        "platform":"Web",
        "browser":"Chrome",
        "device":"No"
        }
    */

//    @ParameterizedTest
//    @ValueSource(Map<String, Object> )
    @Test
    public void checkHeadersTest() {

        UserAgent requestJson = new UserAgent(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36",
                "Web",
                "Chrome",
                "No"
        );
        Map<String,String> header = new HashMap<>();
        header.put("user-agent", requestJson.getUserAgent());
        String url = "https://playground.learnqa.ru/ajax/api/user_agent_check";

        Response response = RestAssured
                .given()
                .headers(header)
                .get(url)
                .andReturn();


        String secretHeaderKey = "x-secret-homework-header";
        String secretHeaderValue = "Some secret value";

//        Assertions.assertHeaderHasSecretData(this.getHeader(response), secretHeaderKey, secretHeaderValue);
        response.prettyPrint();
        System.out.println(response.getBody().asString());

    }
}
