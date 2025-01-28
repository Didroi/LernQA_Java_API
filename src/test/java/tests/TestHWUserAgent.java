package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import lib.Assertions;
import lib.BaseCase;
import lib.ParseUserAgentSource;
import lib.UserAgent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;


public class TestHWUserAgent extends BaseCase {

    static List<UserAgent> provideUserAgents() {
        ParseUserAgentSource parser = new ParseUserAgentSource();
        return parser.parsToRightJson();
    }

    @ParameterizedTest
    @MethodSource("provideUserAgents")
    public void checkParametrizedHeaderTest(UserAgent expected) throws JsonProcessingException {

        String url = "https://playground.learnqa.ru/ajax/api/user_agent_check";
        Map<String,String> header = new HashMap<>();
        header.put("user-agent", expected.getUser_agent());


        Response response = RestAssured
                .given()
                .headers(header)
                .get(url)
                .andReturn();
        response.prettyPrint();

        ObjectMapper objectMapper = new ObjectMapper();
        UserAgent actual = objectMapper.readValue(response.getBody().asString(), UserAgent.class);

        Assertions.assertUserAgentMatches(expected, actual);

    }
}
