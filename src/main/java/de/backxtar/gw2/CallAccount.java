package de.backxtar.gw2;

import com.google.gson.Gson;

import java.io.IOException;

public class CallAccount {

    public static class GWCallAccount {
        public String id;
        public String name;
        public long age;
        public long world;
        public String[] guilds;
        public String[] leader;
        public String created;
        public String[] access;
        public boolean commander;
        public long fractal_level;
        public long daily_ap;
        public long monthly_ap;
        public long wvw_rank;
    }

    public static GWCallAccount getAccount(String token) {
        String json = "";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/account?access_token=" + token);
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            return gson.fromJson(json, GWCallAccount.class);
        } catch (Exception e) {
            return null;
        }
    }
}
