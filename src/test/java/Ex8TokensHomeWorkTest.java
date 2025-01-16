import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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

        response.prettyPrint();
        String token = response.get("token");
        int waitingTime = response.get("seconds");

        Map<String, String> params = new HashMap<>();
        params.put("token", token);

        response = RestAssured
                .given()
                .queryParams(params)
                .get(url)
                .jsonPath();

        response.prettyPrint();
        String status = response.get("status");
        assert status.equals("Job is NOT ready"): "Статус некорректен";

        if(waitingTime > 0) {
            Thread.sleep(waitingTime * 1000L);
        }

        /*
        далее не проверял.
        условие:
        делал бы один запрос c token ПОСЛЕ того, как задача готова, убеждался в правильности поля status и наличии поля result
         */

        response = RestAssured
                .given()
                .queryParams(params)
                .get(url)
                .jsonPath();

        response.prettyPrint();
        String status = response.get("status");
        assert status.equals("Job is NOT ready"): "Статус некорректен";
    }
}
