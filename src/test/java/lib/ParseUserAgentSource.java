package lib;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseUserAgentSource {

    public String getSource() {
        Response response = RestAssured
                .given()
                .get("https://gist.githubusercontent.com/KotovVitaliy/138894aa5b6fa442163561b5db6e2e26/raw/6916020a6a9cf1fbf0ee34c7233ade94d766cc96/some.txt")
                .andReturn();

//        response.prettyPrint();
        String fileContent = response.getBody().asString();

        return fileContent;
    }

    public List<UserAgent> parsToRightJson() {

        List<UserAgent> userAgents = new ArrayList<>();

        return userAgents;
    }
}
