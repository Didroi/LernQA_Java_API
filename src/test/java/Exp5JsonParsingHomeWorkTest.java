import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Exp5JsonParsingHomeWorkTest {

    @Test
    public void exp5JsonParsingHomeWork() {

        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .andReturn();
        response.print();  // Вывод ответа на печать

        String secondMessage = response.jsonPath().getString("messages[1].message");
        System.out.println("\nSecond message: " + secondMessage);
    }
}
