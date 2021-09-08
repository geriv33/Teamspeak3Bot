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
        String request = mode == 0 ? "" : "?tomorrow=true";

        try {
            json = Gw2Utils.getJson("https://aurora-theogenia.de/api/pactsupply/" + request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type callPactSupply = new TypeToken<ArrayList<GWCallPactSupply>>(){}.getType();
        return gson.fromJson(json, callPactSupply);
    }
}
