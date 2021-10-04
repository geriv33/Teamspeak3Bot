package de.backxtar.gw2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CallDaily {

    public static class GWCallDaily {
        public List<PVE> pve;
        public List<PVP> pvp;
        public List<WVW> wvw;
        public List<Fractals> fractals;
        public List<Special> special;
    }

    public static class PVE {
        public int id;
        public Level level;
    }

    public static class PVP {
        public int id;
        public Level level;
    }

    public static class WVW {
        public int id;
        public Level level;
    }

    public static class Fractals {
        public int id;
        public Level level;
    }

    public static class Special {
        public int id;
        public Level level;
    }

    public static class Level {
        public int min;
        public int max;
    }

    public static GWCallDaily getDailies(boolean tomorrow) {
        String json = "";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                if (!tomorrow) json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements/daily?v=latest");
                else json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements/daily/tomorrow?v=latest");
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            return gson.fromJson(json, GWCallDaily.class);
        } catch (Exception e) {
            return null;
        }
    }

    public static class GWCallDailyNames {
        public int id;
        public String name;
    }

    public static ArrayList<GWCallDailyNames> getDailiesName(ArrayList<Integer> ids) {
        String json = "";
        int fails = 0, maxFails = 3;
        StringBuilder idRow = new StringBuilder();

        for (int i = 0; i < ids.size(); i++) {
            idRow.append(ids.get(i));
            if (i < (ids.size() - 1)) idRow.append(",");
        }

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements?ids=" + idRow);
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            Type callNames = new TypeToken<ArrayList<GWCallDailyNames>>() {}.getType();
            return gson.fromJson(json, callNames);
        } catch (Exception e) {
            return null;
        }
    }

    public static class GWCallDailyStrikes {
        public String strike;
        public String day;
    }

    public static ArrayList<GWCallDailyStrikes> getStrikes() {
        String json = "";
        int fails = 0, maxFails = 3;

        while (fails != maxFails) {
            try {
                json = Gw2Utils.getJson("https://api.aleeva.io/data/strike/priority");
                fails = 3;
            } catch (IOException e) {
                if (++fails == 3) e.printStackTrace();
            }
        }
        Gson gson = new Gson();

        try {
            Type callStrikes = new TypeToken<ArrayList<GWCallDailyStrikes>>() {}.getType();
            return gson.fromJson(json, callStrikes);
        } catch (Exception e) {
            return null;
        }
    }
}