package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.TS3Bot;

public class OnClientLeave {
    public static void changeInfo(TS3Api api) {
        if (TS3Bot.ts3Bot.configData.infoChannelID == -1) return;
        api.editChannel(TS3Bot.ts3Bot.configData.infoChannelID, ChannelProperty.CHANNEL_NAME,
                "Users online: " + api.getClients().size() + "/" + api.getServerInfo().getMaxClients()); //TODO channelID anpassen!
    }
}
