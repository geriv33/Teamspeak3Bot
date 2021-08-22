package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.TS3Bot;

public class OnClientLeave {
    public static void changeInfo(TS3Api api) {
        if (Config.getConfigData().infoChannelID == -1) return;
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME,
                "Users online: " + api.getClients().size() + "/" + api.getServerInfo().getMaxClients()); //TODO channelID anpassen!
    }
}
