package lib;

import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assertions {
    public static void assertJsonByName(Response Response, String name, int expectedValue) {
        Response.then().assertThat().body("$", hasKey(name));

        int value = Response.jsonPath().getInt(name);
        assertEquals(expectedValue, value, "Json value is not equal to expected value");
    }

    public static void assertJsonByName(Response Response, String name, String expectedValue) {
        Response.then().assertThat().body("$", hasKey(name));

        String value = Response.jsonPath().getString(name);
        assertEquals(expectedValue, value, "Json value is not equal to expected value");
    }

    public static void assertNameByLength(String name) {
        int length = 15;
//        assert (name.length() > length) : "Длинна меньше 15 символов";
        assertTrue(name.length() > length, "Длинна меньше 15 символов");
    }

    public static void assertNameByLength(String name, int length) {
        assertThat("Длинна меньше 15 символов", name.length(), greaterThan(length));
    }

    public static void assertCookieHasCorrectData(Map<String, String> cookie, String key, String value) {
        assertTrue(cookie.containsKey(key), "Cookie doesn't have key " + key);
        assertEquals(value, cookie.get(key), "Cookies value doesn't " + value);

    }

    public static void assertHeaderHasSecretData(Headers headers, String key, String value) {
        assertTrue(headers.hasHeaderWithName(key), "Response doesn't have header with name " + key);
        assertEquals(value, headers.getValue(key), "Headers(" + key + ") doesn't have correct value: " + value);

    }

    public static void assertUserAgentMatches(UserAgent expected, UserAgent actual) {
        assertEquals(expected.getUser_agent(), actual.getUser_agent(), "user_agent не совпадает");
        assertEquals(expected.getPlatform(), actual.getPlatform(), "platform не совпадает");
        assertEquals(expected.getBrowser(), actual.getBrowser(), "browser не совпадает");
        assertEquals(expected.getDevice(), actual.getDevice(), "device не совпадает");
    }

    public static void assertResponseTextEquals(Response Response, String expectedAnswer) {
        assertEquals(
                expectedAnswer,
                Response.asString(),
                "Response text is not correct"
        );
    }

    public static void assertResponseCodeEquals(Response Response, int expectedStatusCode) {
        assertEquals(
                expectedStatusCode,
                Response.statusCode(),
                "Response status is not correct"
        );
    }

    public static void assertJasonHasField(Response Response, String expectedFieldName) {
        Response.then().assertThat().body("$", hasKey(expectedFieldName));
    }

    public static void assertJasonHasFields(Response Response, String[] expectedFieldNames) {
        for (String expectedFieldName : expectedFieldNames) {
            Assertions.assertJasonHasField(Response, expectedFieldName);
        };
    }

    public static void assetJsonHasNotField(Response Response, String unexpectedFieldName) {
        Response.then().assertThat().body("$", not(hasKey(unexpectedFieldName)));
    }

    public static void assertJasonHasNotFields(Response Response, String[] unexpectedFieldNames) {
        for (String expectedFieldName : unexpectedFieldNames) {
            Assertions.assetJsonHasNotField(Response, expectedFieldName);
        }
    }


}
