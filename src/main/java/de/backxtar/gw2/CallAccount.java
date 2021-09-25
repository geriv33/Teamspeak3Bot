package de.backxtar.gw2;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.google.gson.Gson;
import de.backxtar.DerGeraet;

import java.io.IOException;

public class CallAccount {
    private static final TS3Api api = DerGeraet.getInstance().api;

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

    public static GWCallAccount getAccount(String token, Client client) {
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
            api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] Ups, da funktioniert etwas nicht!");
            return null;
        }
    }
}
