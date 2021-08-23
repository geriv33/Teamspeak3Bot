package de.backxtar.gw2;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.google.gson.Gson;
import de.backxtar.TS3Bot;

import java.io.IOException;

public class CallToken {

    public static class GWCallToken {
        public String id;
        public String name;
        public String[] permissions;
    }

    public static GWCallToken getGWCallToken(String token) {
        String json = "";
        try {
            json = Utils.getJson("https://api.guildwars2.com/v2/tokeninfo?access_token=" + token);
        } catch (IOException ignored) {}

        Gson gson = new Gson();
        try {
            return gson.fromJson(json, GWCallToken.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static void checkToken(Client client) {
        TS3Api api = TS3Bot.getInstance().api;

    }
}
