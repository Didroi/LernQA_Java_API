
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    public void greetings() {
        System.out.println("Hello from Dmitrii");
    }

//    @Test
//    public void firstApiTest() {
//        Response response = RestAssured
//                .get("https://playground.learnqa.ru/api/hello")
//                .andReturn();
//        response.prettyPrint();
//    }
}
