package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.Config;
import de.backxtar.systems.DateTimeClientChannel;

public class OnClientJoin {
    public static void changeInfo(TS3Api api) {
        if (Config.getConfigData().infoChannelID == -1) return;
        String[] dateTime = DateTimeClientChannel.getDateTime();
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME, "Clients: " +
                api.getClients().size() + "/" + api.getServerInfo().getMaxClients() + " | " + dateTime[0] + " | " +
                dateTime[1] + " Uhr");
    }

    public static void sendWelcome(TS3Api api, ClientJoinEvent e) {
        Client client = api.getClientInfo(e.getClientId());
        api.sendPrivateMessage(client.getId(),
                "Willkommen auf " + api.getServerInfo().getName() + ", " + client.getNickname() + "!");
    }
}
