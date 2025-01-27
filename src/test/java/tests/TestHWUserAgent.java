package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.IOException;

import lib.Assertions;
import lib.BaseCase;
import lib.ParseUserAgentSource;
import lib.UserAgent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


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

        UserAgent response = RestAssured
                .given()
                .headers(header)
                .get(url)
                .as(UserAgent.class);


        String secretHeaderKey = "x-secret-homework-header";
        String secretHeaderValue = "Some secret value";

//        Assertions.assertHeaderHasSecretData(this.getHeader(response), secretHeaderKey, secretHeaderValue);
//        response.prettyPrint();
//        System.out.println(response);

    }

    @Test
    public void printAnswer() {
        ParseUserAgentSource ob = new ParseUserAgentSource();
        System.out.println(ob.parsToRightJson());;
//        System.out.println(ob.getSimpleJason());
    }

    static List<UserAgent> provideUserAgents() {
        ParseUserAgentSource parser = new ParseUserAgentSource();
        return parser.parsToRightJson();
    }

    @ParameterizedTest
    @MethodSource("provideUserAgents") // Указываем источник данных
    public void testUserAgentFieldsAreValid(UserAgent userAgent) {

        System.out.println(userAgent.getUserAgent());
        System.out.println(userAgent.getBrowser());
        System.out.println(userAgent.getPlatform());
        // Проверяем, что поле user_agent не пустое и не null
        assertNotNull(userAgent.getUserAgent(), "user_agent должно быть заполнено");
        assertFalse(userAgent.getUserAgent().isEmpty(), "user_agent не должно быть пустым");

        // Проверяем, что поле platform не пустое и не null
        assertNotNull(userAgent.getPlatform(), "platform должно быть заполнено");
        assertFalse(userAgent.getPlatform().isEmpty(), "platform не должно быть пустым");

        // Проверяем, что поле browser не пустое и не null
        assertNotNull(userAgent.getBrowser(), "browser должно быть заполнено");
        assertFalse(userAgent.getBrowser().isEmpty(), "browser не должно быть пустым");

        // Проверяем, что поле device не пустое и не null
        assertNotNull(userAgent.getDevice(), "device должно быть заполнено");
        assertFalse(userAgent.getDevice().isEmpty(), "device не должно быть пустым");
    }
}
