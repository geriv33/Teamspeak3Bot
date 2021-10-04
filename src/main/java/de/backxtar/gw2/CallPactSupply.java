package de.backxtar.gw2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CallPactSupply {
    public static class GWCallPactSupply{
        public String name;
        public String[] location;
    }

    public static ArrayList<GWCallPactSupply> getSupplies(int mode) {
        String json = "";
        String request = mode == 0 ? "" : "&tomorrow=true";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://aurora-theogenia.de/api/pactsupply/?lang=en" + request);
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            Type callPactSupply = new TypeToken<ArrayList<GWCallPactSupply>>() {}.getType();
            return gson.fromJson(json, callPactSupply);
        } catch (Exception e) {
            return null;
        }
    }
}
