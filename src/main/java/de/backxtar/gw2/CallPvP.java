package de.backxtar.gw2;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.google.gson.Gson;
import de.backxtar.DerGeraet;

import java.io.IOException;

public class CallPvP {
    private static final TS3Api api = DerGeraet.getInstance().api;

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

    public static GWCallPvP getPvp(String token, Client client) {
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
            api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] Oops, something's not working!");
            return null;
        }
    }
}
