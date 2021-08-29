package de.backxtar.gw2;

import com.google.gson.Gson;
import de.backxtar.Config;

import java.io.IOException;

public class CallGuild {

    public static class GWCallGuild {
        public long level;
        public String motd;
        public long influence;
        public long aetherium;
        public long resonance;
        public long favor;
        public String id;
        public String name;
        public String tag;
    }

    public static GWCallGuild getGuild(String guildID) {
        String json = "";
        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/guild/" + guildID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(json, GWCallGuild.class);
    }

    public static GWCallGuild getGuildWithLeader(String guildID) {
        String json = "";
        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/guild/" + guildID +
                    "?access_token=" + Config.getConfigData().guildLeaderApiKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(json, GWCallGuild.class);
    }
}
