package de.backxtar.events;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.backxtar.Config;
import de.backxtar.TS3Bot;
import de.backxtar.systems.DateTimeClientChannel;

public class OnClientLeave {
    public static void changeInfo(TS3Api api) {
        if (Config.getConfigData().infoChannelID == -1) return;
        String[] dateTime = DateTimeClientChannel.getDateTime();
        api.editChannel(Config.getConfigData().infoChannelID, ChannelProperty.CHANNEL_NAME, "Clients: " +
                api.getClients().size() + "/" + api.getServerInfo().getMaxClients() + " | " + dateTime[0] + " | " +
                dateTime[1] + " Uhr");
    }
}
