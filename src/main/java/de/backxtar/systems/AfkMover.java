package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import de.backxtar.Config;
import de.backxtar.DerGeraet;

import java.util.HashMap;

public class AfkMover {
    private static final HashMap<String, MoveData> dataHashMap = new HashMap<>();

    private static class MoveData {
        public long timestamp;
        public int channelID;
    }

    public static void checkAfk() {
        if (Config.getConfigData().afkChannelID.length == 0 || Config.getConfigData().afkChannelID[0] == 0) return;
        TS3Api api = DerGeraet.getInstance().api;
        api.getClients().parallelStream().forEach(client -> {
            if (!client.isServerQueryClient()) {
                if (client.isAway() || client.getIdleTime() >= 900000 && client.isInputMuted()
                        || client.getIdleTime() >= 900000 && client.isOutputMuted()) {
                    boolean isAfkChannel = false;

                    for (int id : Config.getConfigData().afkChannelID) {
                        if (client.getChannelId() == id)
                            isAfkChannel = true;
                    }

                    if (!dataHashMap.containsKey(client.getUniqueIdentifier()) && !isAfkChannel) {
                        MoveData moveData = new MoveData();
                        moveData.channelID = client.getChannelId();
                        moveData.timestamp = System.currentTimeMillis() - client.getIdleTime();
                        dataHashMap.put(client.getUniqueIdentifier(), moveData);
                        api.sendPrivateMessage(client.getId(), "Du wurdest in den AFK-Channel verschoben!");
                        api.moveClient(client.getId(), Config.getConfigData().afkChannelID[0]);
                    }
                } else {
                    if (dataHashMap.containsKey(client.getUniqueIdentifier())) {
                        if (client.getChannelId() == Config.getConfigData().afkChannelID[0])
                            api.moveClient(client.getId(), dataHashMap.get(client.getUniqueIdentifier()).channelID);
                        long[] duration = getTime(System.currentTimeMillis() - dataHashMap.get(client.getUniqueIdentifier()).timestamp);
                        String time = (duration[2] > 0 ? duration[2] + "h " : "") +
                                (duration[1] > 0 ? duration[1] + "min " : "") + String.format("%02d", duration[0]) + " sec.";
                        api.sendPrivateMessage(client.getId(), "Deine AFK-Zeit betrug: " + time);
                        dataHashMap.remove(client.getUniqueIdentifier());
                    }
                }
            }
        });
    }

    private static long[] getTime(long timeStamp) {
        long[] time = new long[3];
        time[0] = timeStamp / 1000; // Seconds
        time[1] = time[0] / 60; // Minutes
        time[2] = time[1] / 60; // Hours
        time[1] %= 60;
        time[0] %= 60;
        return time;
    }
}
