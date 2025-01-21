package tests;

import lib.BaseCase;
import lib.Assertions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class TestHWCookie extends BaseCase {

    @Test
    public void checkCookiesTest() {

        Response response = RestAssured
                .given()
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();


        String cookiesKey = "HomeWork";
        String cookiesValue = "hw_value";

        Assertions.assertCookieHasCorrectData(this.getCookies(response), cookiesKey, cookiesValue);
    }
}
