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
    }

    public static class PVE {
        public int id;
    }

    public static class PVP {
        public int id;
    }

    public static class WVW {
        public int id;
    }

    public static class Fractals {
        public int id;
    }

    public static GWCallDaily getDailies(int mode) {
        String json = "";

        try {
            if (mode == 1)
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements/daily");
            else if (mode == 2)
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements/daily/tomorrow");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(json, GWCallDaily.class);
    }

    public static GWCallDaily getDailies(boolean tomorrow) {
        String json = "";

        try {
            if (!tomorrow)
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements/daily");
            else if (tomorrow)
                json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements/daily/tomorrow");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        return gson.fromJson(json, GWCallDaily.class);
    }

    public static class GWCallDailyNames {
        public int id;
        public String name;
    }

    public static ArrayList<GWCallDailyNames> getDailiesName(int[] ids) {
        String json = "";
        StringBuilder idRow = new StringBuilder();

        for (int i = 0; i < ids.length; i++) {
            idRow.append(ids[i]);
            if (i < (ids.length - 1)) idRow.append(",");
        }

        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements?ids=" + idRow);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type callNames = new TypeToken<ArrayList<GWCallDailyNames>>(){}.getType();
        return gson.fromJson(json, callNames);
    }

    public static ArrayList<GWCallDailyNames> getDailiesName(ArrayList<Integer> ids) {
        String json = "";
        StringBuilder idRow = new StringBuilder();

        for (int i = 0; i < ids.size(); i++) {
            idRow.append(ids.get(i));
            if (i < (ids.size() - 1)) idRow.append(",");
        }

        try {
            json = Gw2Utils.getJson("https://api.guildwars2.com/v2/achievements?ids=" + idRow);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type callNames = new TypeToken<ArrayList<GWCallDailyNames>>(){}.getType();
        return gson.fromJson(json, callNames);
    }

    public static class GWCallDailyStrikes {
        public String strike;
        public String day;
    }

    public static ArrayList<GWCallDailyStrikes> getStrikes() {
        String json = "";

        try {
            json = Gw2Utils.getJson("https://api.aleeva.io/data/strike/priority");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Type callStrikes = new TypeToken<ArrayList<GWCallDailyStrikes>>(){}.getType();
        return gson.fromJson(json, callStrikes);
    }
}
