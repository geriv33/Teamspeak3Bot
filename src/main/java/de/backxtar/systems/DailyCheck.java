package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.DerGeraet;
import de.backxtar.gw2.CallDaily;
import de.backxtar.gw2.CallPactSupply;
import de.backxtar.gw2.Gw2Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DailyCheck {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void checkDailies() {
        try {
            if (Config.getConfigData().dailiesChannelID == 0) return;
            CallDaily.GWCallDaily daily = CallDaily.getDailies(1);
            CallDaily.GWCallDaily dailyTomorrow = CallDaily.getDailies(2);

            StringBuilder dailies = checkDailies(daily, 0);
            StringBuilder dailiesTomorrow = checkDailies(dailyTomorrow, 1);

            String des = "[center][size=11][URL=client://" + api.whoAmI().getId() + "/"
                    + api.whoAmI().getUniqueIdentifier() + "]Message me![/URL][/size]" +
                    "\n\n" + dailies + "\n\n\n" + dailiesTomorrow;
            if (api.getChannelInfo(Config.getConfigData().dailiesChannelID).getDescription().equalsIgnoreCase(des))
                return;
            api.editChannel(Config.getConfigData().dailiesChannelID, ChannelProperty.CHANNEL_DESCRIPTION, des);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder checkDailies(CallDaily.GWCallDaily dailies, int mode) {
        int[] idsPvE = getIds(dailies, 1);
        int[] idsPvP = getIds(dailies, 2);
        int[] idsWvW = getIds(dailies, 3);
        int[] idsFractals = getIds(dailies, 4);

        Future<ArrayList<CallDaily.GWCallDailyStrikes>> strikesAsync = executor.submit(CallDaily::getStrikes);
        Future<ArrayList<CallDaily.GWCallDailyNames>> pveAsync = executor.submit(() -> CallDaily.getDailiesName(idsPvE));
        Future<ArrayList<CallDaily.GWCallDailyNames>> pvpAsync = executor.submit(() -> CallDaily.getDailiesName(idsPvP));
        Future<ArrayList<CallDaily.GWCallDailyNames>> wvwAsync = executor.submit(() -> CallDaily.getDailiesName(idsWvW));
        Future<ArrayList<CallDaily.GWCallDailyNames>> fractalsAsync = executor.submit(() -> CallDaily.getDailiesName(idsFractals));
        Future<ArrayList<CallPactSupply.GWCallPactSupply>> pactAsync = executor.submit(() -> CallPactSupply.getSupplies(mode));

        StringBuilder daily = new StringBuilder();
        StringBuilder pve = null;
        StringBuilder pvp = null;
        StringBuilder wvw = null;
        StringBuilder fractals = null;
        StringBuilder recFractals = null;
        StringBuilder pactSupplies = null;

        ArrayList<CallDaily.GWCallDailyStrikes> strikes = null;
        ArrayList<CallDaily.GWCallDailyNames> pveNames;
        ArrayList<CallDaily.GWCallDailyNames> pvpNames;
        ArrayList<CallDaily.GWCallDailyNames> wvwNames;
        ArrayList<CallDaily.GWCallDailyNames> fractalNames;
        ArrayList<CallPactSupply.GWCallPactSupply> supplies;

        try {
            pveNames = pveAsync.get();
            pve = getNames(pveNames, 0);

            pvpNames = pvpAsync.get();
            pvp = getNames(pvpNames, 0);

            wvwNames = wvwAsync.get();
            wvw = getNames(wvwNames, 0);

            fractalNames = fractalsAsync.get();
            fractals = getNames(fractalNames, 1);
            recFractals = getNames(fractalNames, 2);
            strikes = strikesAsync.get();
            supplies = pactAsync.get();
            pactSupplies = getLocations(supplies);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        daily.append("[size=11][color=red][b]").append(mode == 0 ? "Dailies von heute:" : "Dailies von morgen:").append("[/b][/color][/size]\n\n");
        daily.append("[size=10][color=orange][b]PvE Dailies:\n").append("[/b][/color][/size][size=9]").append(pve).append("[/size]\n\n");
        daily.append("[size=10][color=orange][b]PvP Dailies:\n").append("[/b][/color][/size][size=9]").append(pvp).append("[/size]\n\n");
        daily.append("[size=10][color=orange][b]WvW Dailies:\n").append("[/b][/color][/size][size=9]").append(wvw).append("[/size]\n\n");
        daily.append("[size=10][color=orange][b]Fraktal Dailies:\n").append("[/b][/color][/size][size=9]").append(fractals).append("[/size]\n\n");
        daily.append("[size=10][color=orange][b]Daily empfohlene Fraktale:\n").append("[/b][/color][/size][size=9]").append(recFractals).append("[/size]\n");
        daily.append("[size=10][color=orange][b]Daily Strike Mission:\n").append("[/b][/color][/size][img]http://i.epvpimg.com/cfb6fab.png[/img] [size=9]").append(Gw2Utils.formatDailyStrike(strikes.get(mode).strike)).append("[/size]\n\n");
        daily.append("[size=10][color=orange][b]Daily Pakt-Vorratsnetz-Agenten:\n").append("[/b][/color][/size] [size=9]").append(pactSupplies).append("[/size]");

        return daily;
    }

    private static StringBuilder getLocations(ArrayList<CallPactSupply.GWCallPactSupply> supplies) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < supplies.size(); i++) {
            builder.append(supplies.get(i).name).append(" @").append(supplies.get(i).location[1]);
            if (i < (supplies.size() - 1)) builder.append(", ");
        }
        return builder;
    }

    private static int[] getIds(CallDaily.GWCallDaily dailies, int array) {
        int[] ids = null;

        if (array == 1) {
            ids = new int[dailies.pve.size()];

            for (int i = 0; i < dailies.pve.size(); i++) {
                ids[i] = dailies.pve.get(i).id;
            }
        }

        if (array == 2) {
            ids = new int[dailies.pvp.size()];

            for (int i = 0; i < dailies.pvp.size(); i++) {
                ids[i] = dailies.pvp.get(i).id;
            }
        }

        if (array == 3) {
            ids = new int[dailies.wvw.size()];

            for (int i = 0; i < dailies.wvw.size(); i++) {
                ids[i] = dailies.wvw.get(i).id;
            }
        }

        if (array == 4) {
            ids = new int[dailies.fractals.size()];

            for (int i = 0; i < dailies.fractals.size(); i++) {
                ids[i] = dailies.fractals.get(i).id;
            }
        }
        return ids;
    }

    private static StringBuilder getNames(ArrayList<CallDaily.GWCallDailyNames> names, int mode) {
        StringBuilder builder = new StringBuilder();

        if (mode == 0) {
            for (int i = 0; i < names.size(); i++) {
                if (!builder.toString().contains(names.get(i).name)) {
                    String name;
                    if (names.get(i).name.contains("PvP") || names.get(i).name.contains("Top Stats")) {
                        name = Gw2Utils.formatDailiesPvpWvw(names.get(i).name);
                        builder.append("[img]http://i.epvpimg.com/MLQ3fab.png[/img] ").append(name);
                    }
                    else if (names.get(i).name.contains("WvW") || names.get(i).name.contains("Mists Guard Killer")) {
                        name = Gw2Utils.formatDailiesPvpWvw(names.get(i).name);
                        builder.append("[img]http://i.epvpimg.com/WHXtfab.png[/img] ").append(name);
                    }
                    else {
                        name = Gw2Utils.formatDaily(names.get(i).name);
                        builder.append("[img]http://i.epvpimg.com/f6E1cab.png[/img] ").append(name);
                    }
                    if (i < (names.size() - 1)) builder.append("\n");
                }
            }
        }

        if (mode == 1) {
            List<String> unsorted = new ArrayList<>();
            for (CallDaily.GWCallDailyNames gwCallDailyNames : names) {
                if (!builder.toString().contains(gwCallDailyNames.name) && gwCallDailyNames.name.contains("Tier 4")) {
                    String name = Gw2Utils.formatDailyFractals(gwCallDailyNames.name);
                    unsorted.add(name);
                }
            }
            sort(unsorted);

            for (int i = 0; i < unsorted.size(); i++) {
                builder.append("[img]http://i.epvpimg.com/UgDScab.png[/img] ").append(unsorted.get(i));
                if (i < (unsorted.size() - 1)) builder.append("\n");
            }
        }

        if (mode == 2) {
            for (int i = 0; i < names.size(); i++) {
                if (!builder.toString().contains(names.get(i).name) && names.get(i).name.contains("Recommended")) {
                    String name = Gw2Utils.formatRecFractals(names.get(i).name);
                    builder.append("[img]http://i.epvpimg.com/UgDScab.png[/img] ").append(name);
                    if (i < (names.size() - 1)) builder.append("\n");
                }
            }
        }
        return builder;
    }

    private static void sort(List<String> getRecFractals) {
        getRecFractals.sort((o1, o2) -> {
            int s1int = Integer.parseInt(o1.substring(0, o1.indexOf(" ")));
            int s2int = Integer.parseInt(o2.substring(0, o2.indexOf(" ")));
            return s1int - s2int;
        });
    }
}
