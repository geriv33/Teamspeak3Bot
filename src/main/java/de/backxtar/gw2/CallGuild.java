package de.backxtar.gw2;

import com.google.gson.Gson;

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
        public Emblem emblem;
    }

    public static class Emblem {
        public Background background;
        public Foreground foreground;
        public String[] flags;
    }

    public static class Foreground {
        public long id;
        public long[] colors;
    }

    public static class Background {
        public long id;
        public long[] colors;
    }

    public static GWCallGuild getGuild(String guildID) {
        String json = "";
        try {
            json = Utils.getJson("https://api.guildwars2.com/v2/guild/" + guildID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(json, GWCallGuild.class);
    }
}
