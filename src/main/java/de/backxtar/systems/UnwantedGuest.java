package de.backxtar.systems;

import com.github.theholywaffle.teamspeak3.TS3Api;
import de.backxtar.DerGeraet;

public class UnwantedGuest {
    private static final TS3Api api = DerGeraet.getInstance().api;

    public static void checkGuests() {
       //TODO Config
        int defaultChannelID = api.getServerInfo().getDefaultChannelGroup();
        int guest = api.getServerInfo().getDefaultServerGroup();

        api.getClients().forEach(client -> {
            boolean isGuest = false;
            for (int serverGroup : client.getServerGroups()) {
                if (serverGroup == guest) {
                    isGuest = true;
                    break;
                }
            }
            if (!isGuest || client.getChannelId() != defaultChannelID ||
                    api.getClientInfo(client.getId()).getTimeConnected() < 900000) return;
            api.kickClientFromServer(client.getId());
        });
    }
}
