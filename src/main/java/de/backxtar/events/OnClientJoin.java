package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;

public class OnClientJoin
{
    public static void changeInfo(TS3Api api)
    {
        api.editChannel(1, ChannelProperty.CHANNEL_NAME,
                "Users online: " + api.getClients().size() + "/" + api.getServerInfo().getMaxClients()); //TODO channelID anpassen!
    }

    public static void sendWelcome(TS3Api api, ClientJoinEvent e)
    {
        Client client = api.getClientInfo(e.getClientId());
        api.sendPrivateMessage(client.getId(),
                "Willkommen auf " + api.getServerInfo().getName() + ", " + client.getNickname() + "!");
    }
}
