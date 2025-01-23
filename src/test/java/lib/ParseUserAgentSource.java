package lib;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ParseUserAgentSource {

    public void getSource() {
        Response response = RestAssured
                .given()
                .get("https://gist.githubusercontent.com/KotovVitaliy/138894aa5b6fa442163561b5db6e2e26/raw/6916020a6a9cf1fbf0ee34c7233ade94d766cc96/some.txt")
                .andReturn();

        response.prettyPrint();
    }
}
