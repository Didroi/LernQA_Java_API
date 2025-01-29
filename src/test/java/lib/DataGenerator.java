package lib;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class DataGenerator {

    public static String getRandomEmail() {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        return "learnqa" + timestamp + "@email.com";
    }

    public static Map<String, String> getRegistrationData() {
        Map<String, String> data = new HashMap<>();
        data.put("email", DataGenerator.getRandomEmail());
        data.put("password", "1234");
        data.put("username", "lernqa");
        data.put("firstName", "lernqa");
        data.put("LastName", "lernqa");

        return data;
    }

    public static Map<String, String> getRegistrationData(Map<String, String> noneDefaultValues) {
        Map<String, String> defaultValues = DataGenerator.getRegistrationData();

        Map<String, String> userData = new HashMap<>();
        String[] keys = {"email", "password", "username", "firstName", "lastName"};
        for (String key : keys) {
            if (noneDefaultValues.containsKey(key)) {
                userData.put(key, noneDefaultValues.get(key));
            } else {
                userData.put(key, defaultValues.get(key));
            }
        }
    }
}
