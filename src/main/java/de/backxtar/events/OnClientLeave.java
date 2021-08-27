package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.systems.Utils;

public class OnClientLeave {
    public static void changeInfo(TS3Api api) {
        if (Config.getConfigData().infoChannelID == 0) return;
        int clientSize = 0;

        for (Client client : api.getClients()) {
            if (!client.isServerQueryClient())
                clientSize++;
        }
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME, "[cspacer0]Clients: " +
                clientSize + "/" + api.getServerInfo().getMaxClients() + " | " + Utils.getDate());
    }
}
