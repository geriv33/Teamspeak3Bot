package de.backxtar.gw2;

import com.google.gson.Gson;

import java.io.IOException;

public class CallExchange {

    public static class GWCallExchange {
        public int coins_per_gem;
        public int quantity;
    }

    public static GWCallExchange getCoins(boolean isCoin) {
        String currency = isCoin ? "coins" : "gems";
        String amount = isCoin ? "1000000" : "100";
        String json = "";

        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/commerce/exchange/" + currency +
                    "?quantity=" + amount);
        } catch (IOException ignored) {}

        Gson gson = new Gson();
        try {
            return gson.fromJson(json, GWCallExchange.class);
        } catch (Exception e) {
            return null;
        }
    }
}
