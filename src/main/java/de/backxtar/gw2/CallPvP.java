package de.backxtar.gw2;

import com.google.gson.Gson;

import java.io.IOException;

public class CallPvP {

    public static class GWCallPvP {
        public int pvp_rank;
        public int pvp_rank_points;
        public int pvp_rank_rollovers;
        public Aggregate aggregate;
    }

    public static class Aggregate {
        public int wins;
        public int losses;
        public int desertions;
        public int byes;
        public int forfeits;
    }

    public static GWCallPvP getPvp(String token) {
        String json = "";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/pvp/stats?access_token=" + token);
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            return gson.fromJson(json, GWCallPvP.class);
        } catch (Exception e) {
            return null;
        }
    }
}
