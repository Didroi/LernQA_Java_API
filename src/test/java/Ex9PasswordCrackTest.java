import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Ex9PasswordCrackTest {

    //    @Test
    public HashSet<String> returnPassword() throws NullPointerException {

        Response response = io.restassured.RestAssured
                .get("https://en.wikipedia.org/wiki/List_of_the_most_common_passwords")
                .andReturn();

        String html = response.asString();

        Document doc = Jsoup.parse(html);

        Elements tables = doc.select("table.wikitable");
        Element targetTable = null;

        for (Element table : tables) {
            Element caption = table.selectFirst("caption");
            if (caption != null && caption.text().contains("Top 25 most common passwords by year according to SplashData")) {
                targetTable = table;
                break;
            }
        }

        HashSet<String> uniqueValues = new HashSet<>();

        Elements rows = targetTable.select("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cells = row.select("td");

            for (Element cell : cells) {
                uniqueValues.add(cell.text().trim());
            }
        }

//        for (String value : uniqueValues) {
//            System.out.println(value);

        return uniqueValues;
    }

    public String getCookie(String password) {

        Map<String, String> data = new HashMap<>();
        data.put("login", "super_admin");
        data.put("password", password);

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();

        System.out.println("\nPretty text:");
        response.prettyPrint();

        System.out.println("\nHeaders: ");
        Headers responseHeaders = response.getHeaders();
        System.out.println(responseHeaders);

        System.out.println("\nCookies: ");
        Map<String, String> responseCookies = response.getCookies();
        System.out.println(responseCookies);

        String simpleResponseCookie = response.getCookie("auth_cookie");
        System.out.println("\nCoookies value: " + simpleResponseCookie);

        return simpleResponseCookie;
    }

    @Test
    public void crackingPassword() {
        System.out.println(getCookie("sdgsdfsd"));
    }
}
