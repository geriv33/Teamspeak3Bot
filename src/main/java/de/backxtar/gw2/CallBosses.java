package de.backxtar.gw2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CallBosses {

    public static List<String> getRaidBosses(String token) {
        String json = "";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/account/raids?access_token=" + token);
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            Type type = new TypeToken<List<String>>() {}.getType();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }
}
