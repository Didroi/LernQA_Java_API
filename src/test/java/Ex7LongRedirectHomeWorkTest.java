import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Ex7LongRedirectHomeWorkTest {

    @Test
    public void seventhApiTest() {

        int responseStatus = 0;
        String url = "https://playground.learnqa.ru/api/long_redirect";

        while (responseStatus != 200) {

            Response response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();

    //        response.prettyPeek();
            responseStatus = response.getStatusCode();

            String locationHeader = response.getHeader("location");
            System.out.println("\nАдрес Куда нас редиректит:" + locationHeader);
            url = locationHeader;
        }
    }
}
