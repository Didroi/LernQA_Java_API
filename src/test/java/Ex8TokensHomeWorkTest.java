import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Ex8TokensHomeWorkTest {

    @Test
    public void ex8TokensHomeWorkTest() throws InterruptedException {

        String url = "https://playground.learnqa.ru/api/longtime_job";

        JsonPath response = RestAssured
                .given()
                .get(url)
                .jsonPath();

//        response.prettyPrint();
        String token = response.get("token");
        int waitingTime = response.get("seconds");

        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        response = RestAssured
                .given()
                .queryParams(params)
                .get(url)
                .jsonPath();

//        response.prettyPrint();
        String status = response.get("status");
        assert status.equals("Job is NOT ready"): "Статус некорректен";

        if(waitingTime > 0) {
            Thread.sleep(waitingTime * 1000L);
        }

        response = RestAssured
                .given()
                .queryParams(params)
                .get(url)
                .jsonPath();

//        response.prettyPrint();
        status = response.get("status");
        assert status.equals("Job is ready"): "Статус некорректен";
        String result = response.get("result");
        assert result.equals("42"): "Результат некорректен";
    }
}
