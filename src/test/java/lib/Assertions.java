package lib;

import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assertions {
    public static void assertJsonByName(Response Response, String name, int expectedValue) {
        Response.then().assertThat().body("$", hasKey(name));

        int value = Response.jsonPath().getInt(name);
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

}
