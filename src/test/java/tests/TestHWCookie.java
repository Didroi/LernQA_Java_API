package tests;

import lib.BaseCase;
import lib.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestHWCookie extends BaseCase {

    Map<String, String> cookie;

    @Test
    public void checkCookiesTest() {

        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        this.cookie = this.getCookies(response);
        System.out.println(this.cookie);

    }
}
