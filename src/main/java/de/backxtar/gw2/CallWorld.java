package de.backxtar.gw2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CallWorld {

    public static class GWCallWorld {
        public String id;
        public String name;
        public String population;
    }

    public static List<GWCallWorld> getWorld(Long id) {
        String json = "";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/worlds?ids=" + id);
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            Type callWorld = new TypeToken<ArrayList<GWCallWorld>>() {}.getType();
            return gson.<ArrayList<GWCallWorld>>fromJson(json, callWorld);
        } catch (Exception e) {
            return null;
        }
    }
}
