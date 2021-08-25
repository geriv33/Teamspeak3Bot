package de.backxtar.gw2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CallWallet {

    public static class GWCallWallet {
        public int id;
        public long value;
    }

    public static List<GWCallWallet> getWallet(String token) {
        String json = "";
        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/account/wallet?access_token=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type callWorld = new TypeToken<ArrayList<CallWorld.GWCallWorld>>(){}.getType();
        return gson.<ArrayList<GWCallWallet>>fromJson(json, callWorld);
    }
}
