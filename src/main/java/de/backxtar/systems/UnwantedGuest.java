package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import de.backxtar.Config;
import de.backxtar.DerGeraet;

public class UnwantedGuest {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void checkGuests() {
        if (!Config.getConfigData().unwantedGuests) return;
        int guest = api.getServerInfo().getDefaultServerGroup();
        int defaultChannelID = api.whoAmI().getChannelId();

        api.getClients().parallelStream().forEach(client -> {
            if (client.isServerQueryClient()) return;
            boolean isGuest = true;

            for (int serverGroup : client.getServerGroups()) {
                if (serverGroup != guest) {
                    isGuest = false;
                    break;
                }
            }

            if (isGuest && api.getClientInfo(client.getId()).getTimeConnected() >= 300000 &&
                    client.getChannelId() == defaultChannelID) {
                api.kickClientFromServer("You were inactive as a guest in the lobby for 5 minutes!", client.getId());
            }
        });
    }
}
