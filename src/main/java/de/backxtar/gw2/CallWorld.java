package de.backxtar.gw2;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.backxtar.DerGeraet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CallWorld {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static class GWCallWorld {
        public String id;
        public String name;
        public String population;
    }

    public static List<GWCallWorld> getWorld(Long id, Client client) {
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
            api.sendPrivateMessage(client.getId(), "[color=red]✘[/color] Ups, da funktioniert etwas nicht!");
            return null;
        }
    }
}
