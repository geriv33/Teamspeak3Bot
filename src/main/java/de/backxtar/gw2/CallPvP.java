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
        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/pvp/stats?access_token=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(json, GWCallPvP.class);
    }
}
