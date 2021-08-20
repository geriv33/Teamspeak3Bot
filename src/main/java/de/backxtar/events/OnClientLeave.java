package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;

public class OnClientLeave
{
    public static void changeInfo(TS3Api api)
    {
        api.editChannel(1, ChannelProperty.CHANNEL_NAME,
                "Users online: " + api.getClients().size() + "/" + api.getServerInfo().getMaxClients()); //TODO channelID anpassen!
    }
}
