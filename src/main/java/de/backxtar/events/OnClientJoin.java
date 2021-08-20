package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.backxtar.TS3Bot;

public class OnClientJoin {
    public static void changeInfo(TS3Api api) {
        if (TS3Bot.ts3Bot.configData.infoChannelID == 0) return;
        api.editChannel(TS3Bot.ts3Bot.configData.infoChannelID, ChannelProperty.CHANNEL_NAME,
                "Users online: " + api.getClients().size() + "/" + api.getServerInfo().getMaxClients());
    }

    public static void sendWelcome(TS3Api api, ClientJoinEvent e) {
        Client client = api.getClientInfo(e.getClientId());
        api.sendPrivateMessage(client.getId(),
                "Willkommen auf " + api.getServerInfo().getName() + ", " + client.getNickname() + "!");
    }
}
